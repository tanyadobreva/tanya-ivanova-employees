import javax.swing.JFrame;
        import javax.swing.JScrollPane;
        import javax.swing.JTable;

public class PairsTable {

    JFrame f;
    JTable j;

    PairsTable(String[][] theData) {
        f = new JFrame();
        f.setTitle("PairsTable");
        // Column Names
        String[] columnNames = {"Emp1", "Emp2", "Proj", "Days Worked"};
        // Initializing the JTable
        j = new JTable(theData, columnNames);
        j.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
    }
}
