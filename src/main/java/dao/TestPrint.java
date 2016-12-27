package dao;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;

import org.apache.pdfbox.pdfparser.PDFParser;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import model.Item;





public class TestPrint {

	
	
	
	public String executePrint(ArrayList<Item>itemData,String path)
	{
		String result="Printing Failed";
		// PrinterJob pj = PrinterJob.getPrinterJob();
		 Document document = new Document();
		 try {
			 PdfWriter rw=PdfWriter.getInstance(document,
				     new FileOutputStream(path+"Label.pdf"));
		//	 if (pj.printDialog()) {
				document.open();
				PageFormat pf=new PageFormat();
		 	//	pf= pj.pageDialog(pf);
		    //	double height =pf.getHeight();
		 		double height =81.92125984251969;
		 	//	double width =pf.getWidth();
		 		double width =175.748031496063;
		 		
		 		Rectangle one = new Rectangle((float)width,(float) height);
	        	document.setPageSize(one);
	        	document.setMargins(0, 0, 0, 0);
	        	
	        	for (int i=0;i<itemData.size();i++)
		        	{
	        		 //  event.setOrientation(PdfPage.SEASCAPE);
	        			document.newPage();
	        			PdfPTable table = new PdfPTable(1); 
	        			table.setWidthPercentage(100);
	        			float[] columnWidths = {1f};
	        	         table.setWidths(columnWidths);
	        			 PdfPCell cell1 = new PdfPCell();
	        			 PdfPCell cell2 = new PdfPCell();
	        			 PdfPCell cell3 = new PdfPCell();
	        			 PdfPCell cell4;
	        			 cell1.setBorder(0);
	        			 cell2.setBorder(0);
	        			 cell3.setBorder(0);
	        			 Barcode128 c=new Barcode128();
			                c.setCodeType(Barcode128.CODE128);
			                c.setCode(itemData.get(i).getItemCode());
			                PdfContentByte byte1=new PdfContentByte(rw);
			               
			                Image imageEAN = c.createImageWithBarcode(byte1, null, null);
			                
			               
			              //  imageEAN.scaleAbsolute(one.getWidth()/3, one.getHeight()/3);
			                cell4=new PdfPCell(imageEAN,false);
			                cell4.setBorder(0);
			                Font f=new Font();
				             f.setSize(8);
		        		Paragraph itemCodeParagraph=new Paragraph(itemData.get(i).getItemCode(),f);
			             Paragraph brandParagraph=new Paragraph(itemData.get(i).getBrand()+" "+itemData.get(i).getModel(),f);
	        			 Paragraph descParagraph;
	        			 if (itemData.get(i).getDescription().equals("No Info Provided") || itemData.get(i).getDescription().equals(""))
	        				  descParagraph=new Paragraph(new Paragraph("No Description Provided",f));
	        				 else
	        			  descParagraph=new Paragraph(new Paragraph(itemData.get(i).getDescription(),f));
	        			 itemCodeParagraph.setAlignment(Element.ALIGN_CENTER);
	        			 brandParagraph.setAlignment(Element.ALIGN_CENTER);
	        			 descParagraph.setAlignment(Element.ALIGN_CENTER);
	        			 cell1.addElement(itemCodeParagraph);
	        	         cell2.addElement(brandParagraph);
	        	         cell3.addElement(descParagraph);
	        	         cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        	         cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	         cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        	         cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	         cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	         cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	         table.addCell(cell1);
	        	         table.addCell(cell2);
	        	         table.addCell(cell3);
	        	         table.addCell(cell4);
	        	         document.add(table);
		               
					}
	        	 document.close();
	        //	 executePrint(pj.getPrintService().getName());
	        	 result="Printing Initiated"; 
		
		//	 }
		 
		 }
		 catch (DocumentException | FileNotFoundException e) {
					// TODO Auto-generated catch block
			 		result=result+" "+e.toString();
					e.printStackTrace();
				}
		return result;
	}
	
	

	
	
	/*public String executeLabelPrint(String transferID)
	{
		String result="Printing Failed";
		 PrinterJob pj = PrinterJob.getPrinterJob();
		 Document document = new Document();
		 try {
			 PdfWriter rw=PdfWriter.getInstance(document,
				     new FileOutputStream("E:\\test.pdf"));
			 Rotate event = new Rotate();
			 rw.setPageEvent(event);
			
		if (pj.printDialog()) {
				document.open();
				PageFormat pf=new PageFormat();
		 		pf= pj.pageDialog(pf);
		    	double height =pf.getHeight();
		 		double width =pf.getWidth();
		 		Rectangle one = new Rectangle((float)width,(float) height);
	        	document.setPageSize(one);
	        	document.setMargins(0, 0, 0, 0);
	        	PdfPTable table = new PdfPTable(1); 
    			table.setWidthPercentage(100);
    			float[] columnWidths = {1f};
    	         table.setWidths(columnWidths);
    			 PdfPCell cell1 = new PdfPCell();
    			 cell1.setBorder(0);
    			 Paragraph transferIDParagraph=new Paragraph(transferID);
    			 cell1.addElement(transferIDParagraph);
    	         cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
    	         cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    	         table.addCell(cell1);
    	         document.add(table);
	        	 document.close();
	        	 executePrint(pj.getPrintService().getName());
	        	 result="Printing Initiated"; 
		}
		else
		{
			 result="Printing Cancelled By User";
		}
		
		 
		 }
		 catch (DocumentException | FileNotFoundException e) {
					// TODO Auto-generated catch block
			 		result=result+" "+e.toString();
					e.printStackTrace();
				}
		return result;
	}
	
	*/
	
	
	
	
protected static double fromCMToPPI(double cm) {            
    return fromInchtoPPI(cm * 0.393700787);            
}

protected static double fromInchtoPPI(double inch) {            
    return inch * 72;            
}

	 private static ArrayList <String>printerList=new ArrayList<String>();
	public static ArrayList<String> getPrinterList()
	{
		printerList=new ArrayList<String>();
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
	    PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
	   
	    PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);
	    
		 for (int i = 0; i < ps.length; i++)
	     {
	         String svcName = ps[i].toString(); 
	         printerList.add(svcName.replaceFirst("Win32 Printer : ",""));
	     }
		 return printerList;
	}
	
	private static boolean executePrint(String selectedPrinter)
	{
		boolean result=false;
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
	    PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
	   
	    PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);
		PrintService myService = null;
		
	    for (PrintService printService : ps) {
	        if (printService.getName().equals(selectedPrinter)) {
	            myService = printService;
	            Media[] res = (Media[]) printService.getSupportedAttributeValues(Media.class, null, null);
	            for (Media media : res) {
	                if (media instanceof MediaSizeName) {
	                    MediaSizeName msn = (MediaSizeName) media;
	                    MediaSize ms = MediaSize.getMediaSizeForName(msn);
	                    float width = ms.getX(MediaSize.INCH);
	                    
	                    float height = ms.getY(MediaSize.INCH);
	                //    System.out.println(media + ": width = " + width +" "+ ms.getX(MediaSize.MM)+ "; height = " + height+" "+ms.getY(MediaSize.MM));
	                }
	            }
	            break;
	        }
	    }

	    if (myService == null) {
	        throw new IllegalStateException("Printer not found");
	    }

	    FileInputStream fis = null;
		try {
			fis = new FileInputStream("E:\\test.pdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
	    PrintRequestAttributeSet patts2 = new HashPrintRequestAttributeSet();
	    
	    myService.createPrintJob();
	    try {
	    	PrinterJob job = PrinterJob.getPrinterJob();
	    	job.setPrintService(myService);
	    	PDFParser parser = new PDFParser(fis);
	    	parser.parse();
	    	Pageable p=parser.getPDDocument();
	    	
	    	job.setPageable(p);
	    	
	    	job.print(patts2);
	    	fis.close();
	    	result=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return result;
	      
	    }
	 public class Rotate extends PdfPageEventHelper {
		 
	        protected PdfNumber orientation = PdfPage.PORTRAIT;
	 
	        public void setOrientation(PdfNumber orientation) {
	            this.orientation = orientation;
	        }
	 
	        @Override
	        public void onStartPage(PdfWriter writer, Document document) {
	            writer.addPageDictEntry(PdfName.ROTATE, orientation);
	        }
	    }
	 
	
		
}
