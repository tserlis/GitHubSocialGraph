import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class PieChart extends ApplicationFrame {
   static DefaultPieDataset dataset = new DefaultPieDataset(); 
   public PieChart ( String title ) {
      super( title ); 
      setContentPane(createDemoPanel( ));
   }
   
   public void addData(String text,  int val) {
	   double d = (double) val;
	   dataset.setValue(text, d);
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Repo Contributor Comparisons",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(dataset);  
      return new ChartPanel( chart ); 
   }

   public static void main( String[ ] args ) {
      PieChart demo = new PieChart( "Mobile Sales" );  
      demo.setSize( 560 , 367 );    
      RefineryUtilities.centerFrameOnScreen( demo );    
      demo.setVisible( true ); 
   }
}