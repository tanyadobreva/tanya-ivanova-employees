import javax.swing.JFrame;
        import javax.swing.JScrollPane;
        import javax.swing.JTable;

public class PairsTable {

    JFrame f;
    JTable j;

    PairsTable(String[][] theData) {
        f = new JFrame();
        f.setTitle("PairsTable");
        String[] columnNames = {"Emp1", "Emp2", "Proj", "Days Worked"};
        j = new JTable(theData, columnNames);
        j.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        f.setSize(500, 200);
        f.setVisible(true);
    }
}
