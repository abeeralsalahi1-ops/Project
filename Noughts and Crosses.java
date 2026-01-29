import java.util.*;

// 1. Doubly Linked List Node (سجل الحركات)

class HistoryNode {
    String moveData;
    HistoryNode next, prev;
    HistoryNode(String data) { this.moveData = data; }
}

// 2. Doubly Linked List (لإدارة السجل)

class GameHistory {
    private HistoryNode head, tail;
    public void addMove(String msg) {
        HistoryNode newNode = new HistoryNode(msg);
        if (head == null) head = tail = newNode;
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
}
    public void printAll() {
        System.out.println("\n-- سجل المباراة (Doubly Linked List) —");
        HistoryNode temp = head;
        while (temp != null) {
            System.out.print(temp.moveData + (temp.next != null ? " <=> " : ""));
            temp = temp.next;
        }
        System.out.println();
    }
}
// 3. المحرك الأساسي (OOP Engine)

public class TicTacToeGame {
    private char[][] board = new char[3][3]; // Graph Representation
    private Stack<int[]> undoStack = new Stack<>(); // Stack
    private Queue<String> turnQueue = new LinkedList<>(); // Queue
    private GameHistory history = new GameHistory(); // Doubly Linked List
    private Scanner scanner = new Scanner(System.in);

    public TicTacToeGame() {
        // تهيئة اللوحة (Graph Nodes)
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) board[i][j] = '-';

// تهيئة الطابور (Queue) لإدارة الأدوار
      
turnQueue.add("اللاعب X");
        turnQueue.add("اللاعب O");
    }

    public void displayBoard() {
        System.out.println("\n  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    public void start() {
        System.out.println("مرحباً بك في لعبة X-O المتقدمة!");
        int moves = 0;
        while (moves < 9) {
            displayBoard();
            String currentPlayer = turnQueue.poll(); // سحب اللاعب من الطابور
            System.out.println("دور: " + currentPlayer);
            
            System.out.print("أدخل الصف والعمود (مثلاً 0 1): ");
            int r = scanner.nextInt();
            int c = scanner.nextInt();

            if (board[r][c] == '-') {
                char symbol = currentPlayer.endsWith("X") ? 'X' : 'O';
board[r][c] = symbol;
                
                // تخزين في المكدس (Stack) للتراجع مستقبلاً
               
undoStack.push(new int[]{r, c});
                
                // إضافة للسجل (Doubly Linked List)
               
history.addMove(currentPlayer + " في [" + r + "," + c + "]");
                
                // إعادة اللاعب لنهاية الطابور (Queue)
turnQueue.add(currentPlayer);
                moves++;
                
                if (checkWin(symbol)) {
                    displayBoard();
                    System.out.println("تهانينا! " + currentPlayer + " هو الفائز!");
                    break;
                }
            } else {
                System.out.println("المكان مشغول! حاول مجدداً.");
                turnQueue.add(currentPlayer); // إعادة الدور لنفس اللاعب
            }
        }
        history.printAll(); // عرض شجرة السجل في النهاية
    }

    private boolean checkWin(char s) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == s && board[i][1] == s && board[i][2] == s) return true;
            if (board[0][i] == s && board[1][i] == s && board[2][i] == s) return true;
        }
        return (board[0][0] == s && board[1][1] == s && board[2][2] == s) ||
               (board[0][2] == s && board[1][1] == s && board[2][0] == s);
    }
public static void main(String[] args) {
        new TicTacToeGame().start();
    }
}
