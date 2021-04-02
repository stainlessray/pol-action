package dev.raycool.polaction.view;

public class HtmlLineFormatter {

    public String formatAsH2Header(String h2Header) {
        return String.format("<h2>%s</h2>", h2Header);
    }

    public String formatAsH3Header(String h3Header) {
        return String.format("<h3>%s</h3>", h3Header);
    }

    public String formatAsH4Header(String h4Header) {
        return String.format("<h4>%s</h4>", h4Header);
    }

    public String formatAsLink(String urlToFormat) {
        return String.format("<a href = \"%s\">%s</a><br>", urlToFormat, urlToFormat);
    }

    public String formatAsEmailAddress(String emailAddress) {
        return String.format("Email:<br><address>%s</address>", emailAddress);
    }

    public String formatAsPhysicalAddress(String physicalAddress) {
        return String.format("<address>%s</address>", physicalAddress);
    }

    public String formatAsImage(String imageUrl) {
        return String.format("<img src = \"%s\" alt = \"image unavailable\"<br>", imageUrl);
    }

    public String formatAsTelephoneNumber(String phoneNumber) {
        return String.format("<a href = \"tel:%s\">%s</a><br>", phoneNumber, phoneNumber);
    }

    public String formatAsParagraph(String text) {
        return String.format("<p>%s</p>", text);
    }
}
