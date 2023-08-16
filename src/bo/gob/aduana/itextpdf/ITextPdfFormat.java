package bo.gob.aduana.itextpdf;

public class ITextPdfFormat {
    private int size;
    private int tabsIndent;
    private int style;
    private int alignment;
    private int spacing;
    private int font;

    public ITextPdfFormat() {

    }

    /**
     * @param size
     * @param style
     * @param alignment
     * @param spacing
     * @param tabsIndent
     */
    public ITextPdfFormat(int size, int style, int alignment, int spacing, int tabsIndent) {
        this.size = size;
        this.alignment = alignment;
        this.spacing = spacing;
        this.tabsIndent = tabsIndent;
        this.style = style;
    }
    
    /**
     *
     * @param font
     * @param size
     * @param style
     * @param alignment
     * @param spacing
     * @param tabsIndent
     */
    public ITextPdfFormat(int font, int size, int style, int alignment, int spacing, int tabsIndent) {
        this.size = size;
        this.alignment = alignment;
        this.spacing = spacing;
        this.tabsIndent = tabsIndent;
        this.style = style;
        this.font = font;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTabsIndent() {
        return tabsIndent;
    }

    public void setTabsIndent(int tabsIndent) {
        this.tabsIndent = tabsIndent;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int getAlignment() {
        return alignment;
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    public int getSpacing() {
        return spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }
    
    public int getFont(){
        return font;
    }
    
    public void setFont(int font){
        this.font = font;
    }

    public static final int PAGE_LETTER = 1;
    public static final int PAGE_A4 = 2;
    public static final int PAGE_LETTER_HORIZONTAL = 3;
    public static final int PAGE_A4_HORIZONTAL = 4;

    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_LEFT = 2;
    public static final int ALIGN_RIGHT = 3;
    public static final int ALIGN_JUSTIFY = 4;
    
    public static final int VALIGN_MIDDLE = 1;
    public static final int VALIGN_TOP = 2;
    public static final int VALIGN_BOTTOM = 3;

    public static final int STYLE_NORMAL = 1;
    public static final int STYLE_BOLD = 2;
    public static final int STYLE_ITALIC = 3;
    public static final int STYLE_ITALIC_BOLD = 4;
    public static final int STYLE_SUBLINE = 4;

    public static final int SPACING_0 = 1;
    public static final int SPACING_1 = 2;
    public static final int SPACING_1dot5 = 2;
    public static final int SPACING_2 = 3;
    
    public static final int MARGIN_0dot5 = 1;
    public static final int MARGIN_1 = 2;
    public static final int MARGIN_1dot5 = 3;
    public static final int MARGIN_2 = 4;

    public static final int FONT_ARIAL = 1;    
    public static final int FONT_PTSANS = 2;
    public static final int FONT_ROBOTO = 3;
    public static final int FONT_TIMES = 4;

}