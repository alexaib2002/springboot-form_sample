package org.alexaib.springlearn.springbootform.app.editors;

import java.beans.PropertyEditorSupport;

public class CapsNameEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toUpperCase().trim());
    }

}
