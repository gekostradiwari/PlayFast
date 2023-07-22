package utility;

import javax.servlet.http.Part;

public class FileName {
	public static String getFileName(Part part) {
        // Gestire il caso in cui la Part sia null
        if (part == null) {
            return null;
        }

        // Ottenere il nome del file dall'header "content-disposition"
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        // Restituire null se il nome del file non è presente
        return null;
    }

}
