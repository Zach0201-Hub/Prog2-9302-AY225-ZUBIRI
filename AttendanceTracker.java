// Import Swing and AWT libraries
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

// Main class
public class AttendanceTracker {

    public static void main(String[] args) {

        // Create JFrame
        JFrame frame = new JFrame("Attendance Tracker");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // ---------------- TOP PANEL (FORM) ----------------
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Labels
        JLabel nameLabel = new JLabel("Attendance Name:");
        JLabel courseLabel = new JLabel("Course / Year:");
        JLabel timeLabel = new JLabel("Time In:");

        // Text fields
        JTextField nameField = new JTextField();
        JTextField courseField = new JTextField();
        JTextField timeField = new JTextField();

        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeField.setText(now.format(formatter));
        timeField.setEditable(false);

        // Add components to form panel
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(courseLabel);
        formPanel.add(courseField);

        formPanel.add(timeLabel);
        formPanel.add(timeField);

        // ---------------- SIGNATURE PANEL ----------------
        JPanel signatureContainer = new JPanel(new BorderLayout(5, 5));
        signatureContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel signatureLabel = new JLabel("Draw E-Signature Below:");
        SignaturePanel signaturePanel = new SignaturePanel();

        // Clear button
        JButton clearButton = new JButton("Clear Signature");
        clearButton.addActionListener(e -> signaturePanel.clear());

        signatureContainer.add(signatureLabel, BorderLayout.NORTH);
        signatureContainer.add(signaturePanel, BorderLayout.CENTER);
        signatureContainer.add(clearButton, BorderLayout.SOUTH);

        // ---------------- ADD PANELS TO FRAME ----------------
        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(signatureContainer, BorderLayout.CENTER);

        // Show window
        frame.setVisible(true);
    }
}

// --------------------------------------------------
// Signature Panel Class (Drawn E-Signature)
// --------------------------------------------------
class SignaturePanel extends JPanel {

    private Image image;
    private Graphics2D g2;
    private int lastX, lastY;

    public SignaturePanel() {
        setPreferredSize(new Dimension(500, 180));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Mouse pressed
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }
        });

        // Mouse dragged (drawing)
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (g2 != null) {
                    g2.drawLine(lastX, lastY, e.getX(), e.getY());
                    repaint();
                    lastX = e.getX();
                    lastY = e.getY();
                }
            }
        });
    }

    // Create canvas
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            image = createImage(getWidth(), getHeight());
            g2 = (Graphics2D) image.getGraphics();
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    // Clear signature
    public void clear() {
        if (g2 != null) {
            g2.setPaint(Color.WHITE);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setPaint(Color.BLACK);
            repaint();
        }
    }
}
