package dao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import com.itextpdf.text.pdf.Barcode128;
import model.Item;







public class LabelPrint {
	
    public String executePrint(ArrayList<Item>itemData) {
    	String result="Prinitng Failed";
   	 PrinterJob pj = PrinterJob.getPrinterJob();
  
	       
   	 if (pj.printDialog()) {
   		 		PageFormat pf=new PageFormat();
   		 		pf= pj.pageDialog(pf);
   		 		Paper paper = pf.getPaper(); 
	        	double width = fromCMToPPI(pf.getHeight());
	        	double height = fromCMToPPI(pf.getWidth());
	            paper.setSize(width, height);
	            pf.setOrientation(PageFormat.LANDSCAPE);
	            pf.setPaper(paper); 
	            MyPrintable myPrintable=new MyPrintable();
	        	myPrintable.addData(itemData);
	            pj.setPrintable(myPrintable, pf);
	            try {
	            		pj.print();
	            		result="Prinitng Initiated";
	            } catch (PrinterException ex) {
	                ex.printStackTrace();
	            }    
	        } 
   	 
   	 return result;
	    }

protected static double fromCMToPPI(double cm) {            
    return fromInchtoPPI(cm * 0.393700787);            
}

protected static double fromInchtoPPI(double inch) {            
    return inch * 72;            
}



public static class MyPrintable implements Printable {

	ArrayList<Item>itemArray=new ArrayList<Item>();
	public void addData(ArrayList<Item>itemData)
	{
		itemArray=itemData;
	}
	
	
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, 
        int pageIndex) throws PrinterException {    
        int result = NO_SUCH_PAGE; 
        if (pageIndex<itemArray.size())
        {
        	Graphics2D g2d = (Graphics2D) graphics;                    
        //  FontMetrics fm = g2d.getFontMetrics();
        	int i=pageIndex;
            int y=0;
            int counter=0;
            System.out.println(itemArray);
            String text=itemArray.get(i).getItemCode()+"\n"+itemArray.get(i).getSku()+"\n"+itemArray.get(i).getBrand()+" "+itemArray.get(i).getModel()+"\n"+itemArray.get(i).getDescription();
            for (String line : text.split("\n"))
            {
           	 if (counter==3)
           		 
           	 {
           		 Font myFont = new Font ("Times New Roman", 1, 8);
           		 g2d.setFont (myFont);
           	 }
           	 g2d.drawString(line, 0, y += g2d.getFontMetrics().getHeight());
           	 counter++;
            }
                
            Barcode128 c=new Barcode128();
           // BarcodeEAN c=new BarcodeEAN();
           // c.setCodeType(BarcodeEAN.EAN13);
            c.setCodeType(Barcode128.CODE128);
            c.setCode(itemArray.get(i).getItemCode());
            Color c1=new Color(255,255,255);
            Color c2=new Color(0,0,0);
            java.awt.Image imageEAN =c.createAwtImage(c1,c2);
            g2d.drawImage(imageEAN, 20, 75, null);
            result = PAGE_EXISTS;    
        	}
        return result;    
    }
}


}