/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import data.ComboItem;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.text.BadLocationException;

/**
 *
 * @author ibad
 */
public class SearchableComboBox extends JComboBox {
    public int caretPos = 0;
    public JTextField tField = null;

    public SearchableComboBox(final ComboItem items[]) {
        super(items);

        setEditor(new BasicComboBoxEditor());
        setEditable(true);
    }

    public void setSelectedIndex(int index) {
        super.setSelectedIndex(index);

        tField.setText(getItemAt(index).toString());
        tField.setSelectionEnd(caretPos + tField.getText().length());
        tField.moveCaretPosition(caretPos);
    }

    public void setEditor(ComboBoxEditor editor) {
        super.setEditor(editor);

        if(editor.getEditorComponent() instanceof JTextField) {
            tField = (JTextField) editor.getEditorComponent();
            tField.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent ke) {
                    char key = ke.getKeyChar();
                    if(!Character.isLetterOrDigit(key) || Character.isSpaceChar(key)) return;

                    caretPos = tField.getCaretPosition();
                    String text = "";

                    try {
                        text = tField.getText(0, caretPos);
                    } catch(BadLocationException e) { e.printStackTrace(); }

                    for(int i = 0; i < getItemCount(); i++) {
                        ComboItem element = (ComboItem) getItemAt(i);
                        if(element.getVal().startsWith(text)) {
                            setSelectedIndex(i);
                            return;
                        }
                    }
                }
            });
        }
    }
}
