import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {

    @Test
    public void Testget(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<Integer>();
        ad1.addFirst(5);
        ad1.addFirst(6);
        ad1.addFirst(8);
        ad1.addFirst(9);
        ad1.addFirst(1);

        int x1 = ad1.get(0);
        int x2 = ad1.get(2);
        int x3 = ad1.get(3);

        assertThat(x1).isEqualTo(1);
        assertThat(x2).isEqualTo(8);
        assertThat(x3).isEqualTo(6);
    }

    @Test
    public void TestaddFirst(){
        Deque61B<Integer> ad1 = new ArrayDeque61B<Integer>();
        ad1.addFirst(5);
        ad1.addFirst(6);
        ad1.addFirst(8);
        ad1.addFirst(9);
        ad1.addFirst(1);

        assertThat(ad1.toList()).containsExactly(1,9,8,6,5).inOrder();
    }

    @Test
    public void TestAddLast(){
        Deque61B<Integer> ad2 = new ArrayDeque61B<Integer>();
        ad2.addLast(4);
        ad2.addLast(6);
        ad2.addLast(8);
        ad2.addLast(7);
        ad2.addLast(3);

        assertThat(ad2.toList()).containsExactly(4,6,8,7,3).inOrder();
    }

    @Test
    public void TestAddFirstAndAddLast(){
        Deque61B<Integer>ad3 = new ArrayDeque61B<>();
        ad3.addLast(9);
        ad3.addFirst(7);
        ad3.addFirst(8);
        ad3.addLast(6);
        ad3.addLast(0);
        ad3.addFirst(5);
        ad3.addLast(1);

        assertThat(ad3.toList()).containsExactly(5,8,7,9,6,0,1);
    }

    @Test
    public void TestIsEmpty(){
        Deque61B<Character> adno = new ArrayDeque61B<>();
        adno.addFirst('k');
        Deque61B<Integer> adyes = new ArrayDeque61B<>();

        assertThat(adno.isEmpty()).isFalse();
        assertThat(adyes.isEmpty()).isTrue();
    }

    @Test
    public void TestSize(){
        Deque61B<String> ad4 = new ArrayDeque61B<>();
        ad4.addFirst("osp");
        ad4.addLast("ko");
        ad4.addLast("du");

        assertThat(ad4.size()).isEqualTo(3);
    }

    @Test
    public void TestGetFirst(){
        Deque61B<Integer>ad3 = new ArrayDeque61B<>();
        ad3.addLast(9);
        ad3.addFirst(7);
        ad3.addFirst(8);
        ad3.addLast(6);
        ad3.addLast(0);
        ad3.addFirst(5);
        ad3.addLast(1);

        assertThat(ad3.getFirst()).isEqualTo(5);
    }

    @Test
    public void TestGetLast(){
        Deque61B<Integer>ad3 = new ArrayDeque61B<>();
        ad3.addLast(9);
        ad3.addFirst(7);
        ad3.addFirst(8);
        ad3.addLast(6);
        ad3.addLast(0);
        ad3.addFirst(5);
        ad3.addLast(1);

        assertThat(ad3.getLast()).isEqualTo(1);
    }

    @Test
    public void TestRemoveFirst(){
        Deque61B<Integer>ad3 = new ArrayDeque61B<>();
        ad3.addLast(9);
        ad3.addFirst(7);
        ad3.addFirst(8);
        ad3.addLast(6);
        ad3.addLast(0);
        ad3.addFirst(5);
        ad3.addLast(1);

        assertThat(ad3.removeFirst()).isEqualTo(5);
        assertThat(ad3.toList()).containsExactly(8,7,9,6,0,1).inOrder();
    }

    @Test
    public void TestRemoveLast(){
        Deque61B<Integer>ad3 = new ArrayDeque61B<>();
        ad3.addLast(9);
        ad3.addFirst(7);
        ad3.addFirst(8);
        ad3.addLast(6);
        ad3.addLast(0);
        ad3.addFirst(5);
        ad3.addLast(1);

        assertThat(ad3.removeLast()).isEqualTo(1);
        assertThat(ad3.toList()).containsExactly(5,8,7,9,6,0).inOrder();
    }


}
