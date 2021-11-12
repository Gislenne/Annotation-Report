
package annotationreport;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class AnnotationReport {
        
    public static void main(String[] args) throws FileNotFoundException, IOException, DocumentException {  
        String arqPDF = "/home/gislenne/Downloads/DRR223366trated.pdf";
        BufferedReader ler = new BufferedReader(new FileReader("/home/gislenne/Downloads/DRR223366trated.embl")); //leitura do meu arquivo
        String name = "Teste";
        int cds= 0; int product = 0; int gene = 0; int protein = 0; int tRNA= 0; int rRNA = 0;
        while(ler.ready()){ 
            String linha = ler.readLine(); //lendo o arquivo
            if(linha.startsWith("FT   CDS             ")){
                cds++;
            }else if((linha.contains("FT                   /product=\"Hypothetical protein\"")) || (linha.contains("FT                   /product=\"Uncharacterized protein\""))){    
                protein++;
            }else if(linha.startsWith("FT                   /gene=\"")) {
                gene++;
            
            product = (cds - (protein+gene));
            
            }else if(linha.startsWith("FT   tRNA            ")){
                tRNA++;    
            }
            else if(linha.startsWith("FT   RNA             ")){
                rRNA++;    
            }
        }
        
        Document doc = new Document(PageSize.A4);
        
            Font base = FontFactory.getFont(FontFactory.TIMES_ROMAN, 25);
            Font base2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20);
            Font base3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15);

            PdfWriter.getInstance(doc, new FileOutputStream(arqPDF));
            doc.open();

            Image img = Image.getInstance("/home/gislenne/Downloads/LOGO-PNG.png");
            img.setAlignment(Element.ALIGN_CENTER);
            img.scaleAbsolute(120, 120);
            doc.add(img);
            
            Paragraph cabecalho1 = new Paragraph("RENOTEWEB", base);
            cabecalho1.setAlignment(Element.ALIGN_CENTER);
            cabecalho1.setSpacingBefore(10);
            doc.add(cabecalho1);

            Paragraph cabecalho = new Paragraph("ANNOTATION REPORT", base);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(10);
            doc.add(cabecalho);

            Paragraph nameOrg = new Paragraph("Project name: " +name, base2);
            nameOrg.setAlignment(Element.ALIGN_LEFT);
            nameOrg.setSpacingAfter(20); 
            doc.add(nameOrg);

            Paragraph contcds = new Paragraph("Amount of cds: " +cds, base2);
            contcds.setAlignment(Element.ALIGN_LEFT);
            contcds.setSpacingAfter(15); 
            doc.add(contcds);
            
            Paragraph contproduct = new Paragraph("Amount of products: " +product, base2);
            contproduct.setAlignment(Element.ALIGN_LEFT);
            contproduct.setSpacingAfter(15); 
            doc.add(contproduct);
            
            Paragraph contgene = new Paragraph("Number ofproducts with the acronym gene: " +gene, base2);
            contgene.setAlignment(Element.ALIGN_LEFT);
            contgene.setSpacingAfter(15); 
            doc.add(contgene);
            
            
            Paragraph contprotein = new Paragraph("Total of hypothetical proteins: " +protein, base2);
            contprotein.setAlignment(Element.ALIGN_LEFT);
            contprotein.setSpacingAfter(15); 
            doc.add(contprotein);

            Paragraph conttRNA = new Paragraph("Amount of tRNA found: " +tRNA, base2);
            conttRNA.setAlignment(Element.ALIGN_LEFT);
            conttRNA.setSpacingAfter(15); 
            doc.add(conttRNA);

            Paragraph contrRNA = new Paragraph("Amount of rRNA found: " +rRNA, base2);
            contrRNA.setAlignment(Element.ALIGN_LEFT);
            contrRNA.setSpacingAfter(15); 
            doc.add(contrRNA);


            Image img2 = Image.getInstance("/home/gislenne/Downloads/SUPPORT.png");
            img2.setAlignment(Element.ALIGN_CENTER);
            img2.scaleAbsolute(500, 200);
            doc.add(img2);

            doc.close();
            ler.close();
    }
}
    
