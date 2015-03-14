﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using UvA.SoftCon.Questionnaire.Common.AST.Model;

namespace UvA.SoftCon.Questionnaire.QLS.AST.Model.StyleAttributes.Widgets
{
    abstract public class Widget : StyleAttribute
    {
        protected Widget(TextPosition position)
            : base(position) { }

        abstract public bool SupportsDataType(DataType dataType);
    }
}
