package cz.cvut.fel.pjv.logging;

import java.io.IOException;

// import java.io.FileInputStream;
// import java.io.IOException;
// import java.util.logging.FileHandler;
// import java.util.logging.Filter;
// import java.util.logging.Handler;
// import java.util.logging.Level;
// import java.util.logging.LogManager;
// import java.util.logging.LogRecord;
// import java.util.logging.Logger;
// import java.util.logging.SimpleFormatter;
// import java.util.logging.StreamHandler;

// import javax.swing.text.StyleConstants.FontConstants;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
// import com.itextpdf.io.font.constants.StandardFonts;
// import com.itextpdf.kernel.font.PdfFont;
// import com.itextpdf.kernel.font.PdfFontFactory;
// import com.itextpdf.kernel.pdf.PdfDocument;
// import com.itextpdf.kernel.pdf.PdfWriter;
// import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
// import com.itextpdf.layout.element.ListItem;
// import com.itextpdf.layout.element.Paragraph;

import cz.cvut.fel.pjv.utils.Logger;

public class BasicLoggingUsage {

    // private static final Logger LOG =
    // Logger.getLogger(BasicLoggingUsage.class.getName());

    protected static final Logger LOG = new Logger();

    public static void main(String[] args) {
        Thread.currentThread().setName("MainThread");
        // LogManager.getLogManager().readConfiguration(new
        // FileInputStream("C:\\tmp\\logging.properties"));
        // LOG.setLevel(Level.FINEST);
        // LOG.setUseParentHandlers(false);
        // Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
        // @Override
        // public void publish(LogRecord record) {
        // super.publish(record);
        // flush();
        // }

        // @Override
        // public boolean isLoggable(LogRecord record) {
        // return record.getLevel().intValue() >= Level.ALL.intValue();
        // }
        // };
        // LOG.addHandler(stdout);
        // LOG.addHandler(new
        // FileHandler("C:\\Users\\janos\\OneDrive\\Dokumenty\\CVUT\\S02\\PJV\\cv07\\log.txt",
        // false));
        LOG.error("Error message");
        LOG.warn("Warn message");
        LOG.info("Info message");
        LOG.debug("Debug message");
        // LOG.trace("Trace message");
        LOG.trace(new Exception("Division by zero"));
        PdfWriter writer;
        try {
            LOG.info("Creating PDF document...");
            writer = new PdfWriter(
                    "C:\\Users\\janos\\OneDrive\\Dokumenty\\CVUT\\S02\\PJV\\cv07_NEEXISTUJE!!!!\\output.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            PdfFont font = PdfFontFactory.createFont();
            document.add(new Paragraph("iText is:").setFont(font));
            List list = new List()
                    .setSymbolIndent(12)
                    .setListSymbol("\u2022")
                    .setFont(font);
            list.add(new ListItem("Never gonna give you up"))
                    .add(new ListItem("Never gonna let you down"))
                    .add(new ListItem("Never gonna run around and desert you"))
                    .add(new ListItem("Never gonna make you cry"))
                    .add(new ListItem("Never gonna say goodbye"))
                    .add(new ListItem("Never gonna tell a lie and hurt you"));
            document.add(list);
            document.close();
        } catch (IOException e) {
            LOG.error("Error creating PDF document", e);
            LOG.trace(e);
        }
    }
}
