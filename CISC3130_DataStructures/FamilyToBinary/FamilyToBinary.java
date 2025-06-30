import java.util.*;

class FamilyNode {
    String name;
    FamilyNode firstSon, nextBrother, father;

    FamilyNode(String name) {
        this.name = name;
    }
}

public class FamilyToBinary {

    public static FamilyNode buildFamilyTree(Scanner sc) {
        FamilyNode root = new FamilyNode(sc.next());
        int numSons = sc.nextInt();
        root.firstSon = buildSons(sc, numSons, root);
        return root;
    }

    public static FamilyNode buildSons(Scanner sc, int count, FamilyNode father) {
        FamilyNode first = null, prev = null;
        for (int i = 0; i < count; i++) {
            FamilyNode son = new FamilyNode(sc.next());
            int grandsons = sc.nextInt();
            son.father = father;
            if (first == null) first = son;
            else prev.nextBrother = son;
            son.firstSon = buildSons(sc, grandsons, son);
            prev = son;
        }
        return first;
    }

    public static FamilyNode findNode(FamilyNode node, String name) {
        if (node == null) return null;
        if (node.name.equals(name)) return node;
        FamilyNode found = findNode(node.firstSon, name);
        return (found != null) ? found : findNode(node.nextBrother, name);
    }

    public static String findFather(FamilyNode root, String name) {
        FamilyNode node = findNode(root, name);
        return (node != null && node.father != null) ? node.father.name : "No father found (root or not in tree)";
    }

    public static List<String> getSiblings(FamilyNode start, String exclude) {
        List<String> list = new ArrayList<>();
        while (start != null) {
            if (!start.name.equals(exclude)) list.add(start.name);
            start = start.nextBrother;
        }
        return list;
    }

    public static List<String> findSons(FamilyNode root, String name) {
        FamilyNode node = findNode(root, name);
        return (node != null && node.firstSon != null) ? getSiblings(node.firstSon, "") : new ArrayList<>();
    }

    public static List<String> findBrothers(FamilyNode root, String name) {
        FamilyNode node = findNode(root, name);
        return (node != null && node.father != null) ? getSiblings(node.father.firstSon, name) : new ArrayList<>();
    }

    public static List<String> findUncles(FamilyNode root, String name) {
        FamilyNode node = findNode(root, name);
        return (node != null && node.father != null && node.father.father != null)
                ? getSiblings(node.father.father.firstSon, node.father.name)
                : new ArrayList<>();
    }

    public static String findOldestBrother(FamilyNode root, String name) {
        FamilyNode node = findNode(root, name);
        if (node != null && node.father != null) {
            FamilyNode first = node.father.firstSon;
            return (first != null && !first.name.equals(name)) ? first.name : findYoungestBrother(root, name);
        }
        return "No brothers";
    }

    public static String findYoungestBrother(FamilyNode root, String name) {
        FamilyNode node = findNode(root, name);
        if (node != null && node.father != null) {
            FamilyNode curr = node.father.firstSon, last = null;
            while (curr != null) {
                if (!curr.name.equals(name)) last = curr;
                curr = curr.nextBrother;
            }
            return (last != null) ? last.name : "No brothers";
        }
        return "No brothers";
    }

    public static String findOldestSon(FamilyNode root, String name) {
        FamilyNode node = findNode(root, name);
        return (node != null && node.firstSon != null) ? node.firstSon.name : "No sons";
    }

    public static String findYoungestSon(FamilyNode root, String name) {
        FamilyNode node = findNode(root, name);
        if (node != null && node.firstSon != null) {
            FamilyNode curr = node.firstSon;
            while (curr.nextBrother != null) curr = curr.nextBrother;
            return curr.name;
        }
        return "No sons";
    }

    public static String findGrandfather(FamilyNode root, String name) {
        FamilyNode node = findNode(root, name);
        return (node != null && node.father != null && node.father.father != null) ? node.father.father.name : "No grandfather found";
    }

    public static void printTree(FamilyNode node, String indent) {
        if (node == null) return;
        System.out.println(indent + node.name);
        printTree(node.firstSon, indent + "  ");
        printTree(node.nextBrother, indent);
    }

    public static String formatList(List<String> list, String emptyMsg) {
        return list.isEmpty() ? emptyMsg : String.join(", ", list);
    }

    public static void askQuestions(FamilyNode root, String name) {
        System.out.println("\n--- Family Facts: " + name + " ---");
        System.out.println("Father: " + findFather(root, name));
        System.out.println("Sons: " + formatList(findSons(root, name), "No sons"));
        System.out.println("Brothers: " + formatList(findBrothers(root, name), "No brothers"));
        System.out.println("Oldest Brother: " + findOldestBrother(root, name));
        System.out.println("Youngest Brother: " + findYoungestBrother(root, name));
        System.out.println("Oldest Son: " + findOldestSon(root, name));
        System.out.println("Youngest Son: " + findYoungestSon(root, name));
        System.out.println("Uncles: " + formatList(findUncles(root, name), "No uncles found"));
        System.out.println("Grandfather: " + findGrandfather(root, name));
    }

    public static void printCentered(String text) {
        int width = 50;
        int padding = (width - text.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + text);
    }

    public static void main(String[] args) {
        String[] inputs = {
                "Marlin 3 Nemo 2 Anchor 0 Crush 1 Squirt 0 Jacques 1 Bruce 1 Gill 0 Bloat 0",
                "Mufasa 2 Simba 1 Banzai 0 Timon 0 Pumbaa 0",
                "Jookiba 2 Stitch 1 Pleakley 0 David 1 Gantu 0"
        };
        String[][] persons = {
                {"Nemo", "Jacques"},
                {"Simba", "Mufasa"},
                {"Stitch", "David"}
        };

        for (int i = 0; i < inputs.length; i++) {
            printCentered("*** Tree " + (i + 1) + " ***\n");
            FamilyNode root = buildFamilyTree(new Scanner(inputs[i]));
            printTree(root, "");
            for (String person : persons[i]) askQuestions(root, person);
        }
    }
}
