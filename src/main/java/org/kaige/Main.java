package org.kaige;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class Main {
	public static FileInputStream src;
	public static FileOutputStream dest;

	public static void init() {
		try {
			src = new FileInputStream(new File("in.pdf"));
			dest = new FileOutputStream(new File("out.pdf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		init();
		addHeader(src, dest);
	}

	public static void hello(String[] args) {
		PdfWriter writer = new PdfWriter(dest);

		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);
		document.add(new Paragraph("Hello World!"));
		document.close();
	}

	public static void addHeader(FileInputStream src, FileOutputStream dest) {
		PdfDocument pdfDoc =
				null;
		try {
			pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document document = new Document(pdfDoc);
		Rectangle pageSize;
		PdfCanvas canvas;
		int n = pdfDoc.getNumberOfPages();
		for (int i = 1; i <= n; i++) {
			PdfPage page = pdfDoc.getPage(i);
			pageSize = page.getPageSize();
			canvas = new PdfCanvas(page);
// add new content
			//Draw header text
			try {
				canvas.beginText().setFontAndSize(
						PdfFontFactory.createFont(FontConstants.HELVETICA), 7)
						.moveText(pageSize.getWidth() / 2 - 24, 0)
						.showText("I want to believe")
						.endText();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		pdfDoc.close();
	}

	public static void read(String[] args) {
		PdfReaderContentParser p;
	}
}

