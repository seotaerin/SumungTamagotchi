//출처: https://stackoverflow.com/questions/76639548/i-need-to-add-a-new-font-in-my-java-project

package tama;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

class LoadFontDemo {

    public static Font loadFontFromResource(Class theClass, String fontFileName, float fontSize) {
        float fntSize = 12f;

        if (fontSize >= 1f) {
            fntSize = fontSize;
        }

        // Load in the supplied font file from resources.
        InputStream is = theClass.getResourceAsStream("Galmuri9.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(fntSize);
        }
        catch (FontFormatException | IOException ex) {
            System.err.println("Error Loading Font (" + fontFileName
                    + ")! Here is why:\n" + ex.getMessage());
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                }
                catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
        return font;
    }
}
