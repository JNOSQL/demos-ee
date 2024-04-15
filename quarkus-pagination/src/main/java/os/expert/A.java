package os.expert;

import jakarta.data.page.PageRequest;

public class A {

    public static void main(String[] args) {
        PageRequest offSet = PageRequest.ofPage(1).size(10);
        PageRequest cursor = PageRequest.ofSize(10).afterCursor(PageRequest.Cursor.forKey("key"));
    }
}
