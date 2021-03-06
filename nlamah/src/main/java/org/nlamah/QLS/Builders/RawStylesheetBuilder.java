package org.nlamah.QLS.Builders;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.nlamah.QBase.Constants.QBaseQuestionType;
import org.nlamah.QBase.Error.EnumRecognitionError;
import org.nlamah.QBase.Error.QBaseParsingError;
import org.nlamah.QBase.Model.QBaseNode;
import org.nlamah.QBase.Tools.QLSTools;
import org.nlamah.QBase.Tools.StringTools;
import org.nlamah.QL.Model.Expression.Literal.BooleanLiteral;
import org.nlamah.QL.Model.Expression.Literal.NumberLiteral;
import org.nlamah.QL.Model.Expression.Literal.TextLiteral;
import org.nlamah.QLS.QLSBaseVisitor;
import org.nlamah.QLS.QLSParser;
import org.nlamah.QLS.QLSParser.DefaultBlockContext;
import org.nlamah.QLS.QLSParser.PageContext;
import org.nlamah.QLS.QLSParser.SectionContext;
import org.nlamah.QLS.QLSParser.StyleDeclarationContext;
import org.nlamah.QLS.QLSParser.StylesheetBlockContext;
import org.nlamah.QLS.Error.DoublePropertyDeclarationError;
import org.nlamah.QLS.Error.FontRecognitionError;
import org.nlamah.QLS.Model.Abstract.QLSNode;
import org.nlamah.QLS.Model.Abstract.SectionItem;
import org.nlamah.QLS.Model.Abstract.StyleDeclaration;
import org.nlamah.QLS.Model.Declaration.ColorDeclaration;
import org.nlamah.QLS.Model.Declaration.FontDeclaration;
import org.nlamah.QLS.Model.Declaration.FontSizeDeclaration;
import org.nlamah.QLS.Model.Declaration.WidgetDeclaration;
import org.nlamah.QLS.Model.Declaration.WidthDeclaration;
import org.nlamah.QLS.Model.StylesheetBlock.DefaultBlock;
import org.nlamah.QLS.Model.StylesheetBlock.Page;
import org.nlamah.QLS.Model.StylesheetBlock.StyleBlock;
import org.nlamah.QLS.Model.StylesheetBlock.StyledQuestion;
import org.nlamah.QLS.Model.StylesheetBlock.Stylesheet;
import org.nlamah.QLS.Model.StylesheetBlock.Section;
import org.nlamah.QLS.Model.Value.IdentifierValue;
import org.nlamah.QLS.Model.Value.WidgetTypeEnum;

public class RawStylesheetBuilder extends QLSBaseVisitor<QLSNode> 
{
	private List<QBaseParsingError> errors;

	private int sectionDepthLevel = -1;

	public RawStylesheetBuilder() 
	{
		errors = new ArrayList<QBaseParsingError>();
	}

	public List<QBaseParsingError> errors()
	{
		return this.errors;
	}

	public Stylesheet build(ParseTree tree)
	{
		return (Stylesheet) tree.accept(this);
	}

	private void checkForDoublePropertyDeclarations(StyleDeclaration styleDeclaration, List<StyleDeclaration> styleDeclarations)
	{
		List<StyleDeclaration> foundDeclarations = QLSTools.findStyleDeclarationsOfTheSameClass(styleDeclaration, styleDeclarations);

		if (foundDeclarations.size() > 0)
		{
			foundDeclarations.add(styleDeclaration);

			errors.add(new DoublePropertyDeclarationError(foundDeclarations));
		}
	}

	@Override 
	public QLSNode visitStylesheet(QLSParser.StylesheetContext ctx) 
	{ 		
		IdentifierValue identifier = new IdentifierValue(ctx.Identifier().getText());
		QBaseNode.addSourceCodePosition(identifier, ctx);

		List<Page> pages = new ArrayList<Page>();

		for (PageContext contextualPage : ctx.page())
		{
			Page page = (Page) contextualPage.accept(this);
			pages.add(page);
		}

		List<DefaultBlock> defaultBlocks = new ArrayList<DefaultBlock>();

		for (DefaultBlockContext contextualDefaultBlock : ctx.defaultBlock())
		{
			DefaultBlock defaultBlock = (DefaultBlock) contextualDefaultBlock.accept(this);
			defaultBlocks.add(defaultBlock);
		}

		Stylesheet stylesheet = new Stylesheet(identifier, pages, defaultBlocks);
		QBaseNode.addSourceCodePosition(stylesheet, ctx);

		return stylesheet;
	}

	@Override 
	public QLSNode visitPage(QLSParser.PageContext ctx) 
	{ 
		IdentifierValue identifier = new IdentifierValue(ctx.Identifier().getText());
		QBaseNode.addSourceCodePosition(identifier, ctx);

		List<Section> sections = new ArrayList<Section>();

		for (SectionContext contextualSection : ctx.section())
		{
			Section section = (Section) contextualSection.accept(this);
			sections.add(section);
		}

		List<DefaultBlock> defaultBlocks = new ArrayList<DefaultBlock>();

		for (DefaultBlockContext contextualDefaultBlock : ctx.defaultBlock())
		{
			DefaultBlock defaultBlock = (DefaultBlock) contextualDefaultBlock.accept(this);
			defaultBlocks.add(defaultBlock);
		}

		Page page = new Page(identifier, sections, defaultBlocks);
		QBaseNode.addSourceCodePosition(page, ctx);

		return page;
	}

	@Override 
	public QLSNode visitSection(QLSParser.SectionContext ctx) 
	{
		sectionDepthLevel++;

		String title = StringTools.removeSurroundingQuotes(ctx.Text().getText());

		List<SectionItem> sectionItems = new ArrayList<SectionItem>();

		for (StylesheetBlockContext contextualSection : ctx.stylesheetBlock())
		{
			SectionItem sectionItem = (SectionItem) contextualSection.accept(this);
			sectionItems.add(sectionItem);
		}

		List<DefaultBlock> defaultBlocks = new ArrayList<DefaultBlock>();

		for (DefaultBlockContext contextualDefaultBlock : ctx.defaultBlock())
		{
			DefaultBlock defaultBlock = (DefaultBlock) contextualDefaultBlock.accept(this);
			defaultBlocks.add(defaultBlock);
		}

		Section section = new Section(title, sectionItems, defaultBlocks, sectionDepthLevel);
		QBaseNode.addSourceCodePosition(section, ctx);

		sectionDepthLevel--;

		return section;
	}

	@Override 
	public QLSNode visitStyledQuestion(QLSParser.StyledQuestionContext ctx)
	{ 
		IdentifierValue identifier = new IdentifierValue(ctx.Identifier().getText());
		QBaseNode.addSourceCodePosition(identifier, ctx);

		List<StyleDeclaration> styleDeclarations = new ArrayList<StyleDeclaration>();

		for (StyleDeclarationContext contextualStyleDeclaration : ctx.styleDeclaration())
		{
			StyleDeclaration styleDeclaration = (StyleDeclaration)contextualStyleDeclaration.accept(this);

			checkForDoublePropertyDeclarations(styleDeclaration, styleDeclarations);

			styleDeclarations.add(styleDeclaration);
		}

		StyleBlock styleBlock = new StyleBlock(styleDeclarations);
		QBaseNode.addSourceCodePosition(styleBlock, ctx);


		StyledQuestion styledQuestion = new StyledQuestion(identifier, styleBlock);
		QBaseNode.addSourceCodePosition(styledQuestion, ctx);

		return styledQuestion; 
	}

	@Override
	public QLSNode visitDefaultBlock(QLSParser.DefaultBlockContext ctx)
	{
		String questionTypeString = ctx.QuestionType() != null ? ctx.QuestionType().getText().toUpperCase() : null;

		QBaseQuestionType questionType = null;

		if (questionTypeString != null)
		{
			try 
			{
				questionType = QBaseQuestionType.valueOf(questionTypeString);
			} 
			catch(Exception ex) 
			{	
				errors.add(new EnumRecognitionError(questionTypeString, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()));
			}
		}

		List<StyleDeclaration> styleDeclarations = new ArrayList<StyleDeclaration>();

		for (StyleDeclarationContext contextualStyleDeclaration : ctx.styleDeclaration())
		{
			StyleDeclaration styleDeclaration = (StyleDeclaration)contextualStyleDeclaration.accept(this);

			checkForDoublePropertyDeclarations(styleDeclaration, styleDeclarations);

			styleDeclarations.add(styleDeclaration);
		}

		DefaultBlock defaultBlock = new DefaultBlock(questionType, styleDeclarations);
		QBaseNode.addSourceCodePosition(defaultBlock, ctx);

		return defaultBlock;
	}

	@Override 
	public QLSNode visitWidthDeclaration(QLSParser.WidthDeclarationContext ctx) 
	{ 
		String numberValueString = ctx.Number().getText();

		int numberValue = Integer.parseInt(numberValueString); 

		WidthDeclaration widthDeclaration = new WidthDeclaration(numberValue);
		QBaseNode.addSourceCodePosition(widthDeclaration, ctx);

		return widthDeclaration;
	}

	@Override 
	public QLSNode visitFontDeclaration(QLSParser.FontDeclarationContext ctx) 
	{ 
		String fontValueString = StringTools.removeSurroundingQuotes(ctx.Text().getText());

		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

		String[] availableFontFamilyNames = graphicsEnvironment.getAvailableFontFamilyNames();

		if (Arrays.asList(availableFontFamilyNames).indexOf(fontValueString) < 0)
		{
			errors.add(new FontRecognitionError(fontValueString, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()));
		}

		Font font = Font.decode(fontValueString);

		FontDeclaration fontDeclaration = new FontDeclaration(font);
		QBaseNode.addSourceCodePosition(fontDeclaration, ctx);

		return fontDeclaration; 
	}

	@Override 
	public QLSNode visitFontSizeDeclaration(QLSParser.FontSizeDeclarationContext ctx) 
	{ 
		String numberValueString = ctx.Number().getText();

		int numberValue = Integer.parseInt(numberValueString); 

		FontSizeDeclaration fontSizeDeclaration = new FontSizeDeclaration(numberValue);
		QBaseNode.addSourceCodePosition(fontSizeDeclaration, ctx);

		return fontSizeDeclaration;
	}

	@Override 
	public QLSNode visitColorDeclaration(QLSParser.ColorDeclarationContext ctx) 
	{ 
		String hexNumberValueString = ctx.HexNumber().getText();

		hexNumberValueString = QLSTools.uniformHexNumberString(hexNumberValueString);

		Color color = Color.decode(hexNumberValueString);

		ColorDeclaration colorDeclaration = new ColorDeclaration(color);
		QBaseNode.addSourceCodePosition(colorDeclaration, ctx);

		return colorDeclaration;
	}

	@Override 
	public QLSNode visitWidgetDeclaration(QLSParser.WidgetDeclarationContext ctx) 
	{ 
		WidgetDeclaration widgetDeclaration = (WidgetDeclaration) ctx.widgetStyle().accept(this);

		QBaseNode.addSourceCodePosition(widgetDeclaration, ctx);

		return widgetDeclaration;
	}

	@Override 
	public QLSNode visitCheckBox(QLSParser.CheckBoxContext ctx) 
	{ 		
		WidgetDeclaration widgetDeclaration = new WidgetDeclaration(WidgetTypeEnum.CHECKBOX, QBaseQuestionType.BOOLEAN, null);

		QBaseNode.addSourceCodePosition(widgetDeclaration, ctx);

		return widgetDeclaration; 
	}

	@Override 
	public QLSNode visitSpinBox(QLSParser.SpinBoxContext ctx) 
	{
		WidgetDeclaration widgetDeclaration = new WidgetDeclaration(WidgetTypeEnum.SPINBOX, QBaseQuestionType.NUMBER, null);

		QBaseNode.addSourceCodePosition(widgetDeclaration, ctx);

		return widgetDeclaration;  
	}

	@Override 
	public QLSNode visitRadioButtonText(QLSParser.RadioButtonTextContext ctx) 
	{ 
		Map<TextLiteral, TextLiteral> answers = new LinkedHashMap<TextLiteral, TextLiteral>();

		for (org.antlr.v4.runtime.Token contextualTextValue : ctx.answer)
		{
			TextLiteral answer = new TextLiteral(StringTools.removeSurroundingQuotes(contextualTextValue.getText()));
			QBaseNode.addSourceCodePosition(answer, ctx);
			answers.put(answer, answer);
		}

		WidgetDeclaration widgetDeclaration = new WidgetDeclaration(WidgetTypeEnum.RADIOBUTTON, QBaseQuestionType.TEXT, answers);

		QBaseNode.addSourceCodePosition(widgetDeclaration, ctx);

		return widgetDeclaration; 
	}

	@Override 
	public QLSNode visitRadioButtonNumber(QLSParser.RadioButtonNumberContext ctx) 
	{ 
		Map<TextLiteral, NumberLiteral> answers = new LinkedHashMap<TextLiteral, NumberLiteral>();

		for (org.antlr.v4.runtime.Token contextualNumberValue : ctx.answer)
		{
			String numberValueString = StringTools.removeSurroundingQuotes(contextualNumberValue.getText());
			TextLiteral answer = new TextLiteral(numberValueString);
			NumberLiteral answerValue = new NumberLiteral(Integer.valueOf(numberValueString));
			QBaseNode.addSourceCodePosition(answer, ctx);
			QBaseNode.addSourceCodePosition(answerValue, ctx);
			answers.put(answer, answerValue);
		}

		WidgetDeclaration widgetDeclaration = new WidgetDeclaration(WidgetTypeEnum.RADIOBUTTON, QBaseQuestionType.NUMBER, answers);

		QBaseNode.addSourceCodePosition(widgetDeclaration, ctx);

		return widgetDeclaration; 
	}

	@Override 
	public QLSNode visitRadioButtonBoolean(QLSParser.RadioButtonBooleanContext ctx) 
	{ 
		Map<TextLiteral, BooleanLiteral> answers = new LinkedHashMap<TextLiteral, BooleanLiteral>();

		for (org.antlr.v4.runtime.Token contextualBooleanValue : ctx.answer)
		{
			String booleanValueString = StringTools.removeSurroundingQuotes(contextualBooleanValue.getText());
			TextLiteral answer = new TextLiteral(booleanValueString);
			BooleanLiteral answerValue = new BooleanLiteral(booleanValueString.equals("yes") ? true : false);
			QBaseNode.addSourceCodePosition(answer, ctx);
			QBaseNode.addSourceCodePosition(answerValue, ctx);
			answers.put(answer, answerValue);
		}

		WidgetDeclaration widgetDeclaration = new WidgetDeclaration(WidgetTypeEnum.RADIOBUTTON, QBaseQuestionType.BOOLEAN, answers);

		QBaseNode.addSourceCodePosition(widgetDeclaration, ctx);

		return widgetDeclaration; 
	}

	@Override
	public QLSNode visitTextField(QLSParser.TextFieldContext ctx)
	{
		return null;
	}

	@Override
	public QLSNode visitNumberField(QLSParser.NumberFieldContext ctx)
	{
		return null;
	}
}