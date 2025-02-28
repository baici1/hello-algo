---
comments: true
---

# 栈

「栈 Stack」是一种遵循「先入后出 first in, last out」数据操作规则的线性数据结构。我们可以将栈类比为放在桌面上的一摞盘子，如果需要拿出底部的盘子，则需要先将上面的盘子依次取出。

我们将顶部盘子称为「栈顶」，底部盘子称为「栈底」，将把元素添加到栈顶的操作称为「入栈」，将删除栈顶元素的操作称为「出栈」。

![stack_operations](stack.assets/stack_operations.png)

<p align="center"> Fig. 栈的先入后出特性 </p>

## 栈常用操作

栈的常用操作见下表，方法名需根据编程语言设定来具体确定。

<p align="center"> Table. 栈的常用操作 </p>

<div class="center-table" markdown>

| 方法      | 描述                   |
| --------- | ---------------------- |
| push()    | 元素入栈（添加至栈顶） |
| pop()     | 栈顶元素出栈           |
| peek()    | 访问栈顶元素           |
| size()    | 获取栈的长度           |
| isEmpty() | 判断栈是否为空         |

</div>

我们可以直接使用编程语言实现好的栈类。

=== "Java"

    ```java title="stack.java"
    /* 初始化栈 */
    // 在 Java 中，推荐将 LinkedList 当作栈来使用
    LinkedList<Integer> stack = new LinkedList<>();

    /* 元素入栈 */
    stack.addLast(1);
    stack.addLast(3);
    stack.addLast(2);
    stack.addLast(5);
    stack.addLast(4);

    /* 访问栈顶元素 */
    int peek = stack.peekLast();

    /* 元素出栈 */
    int pop = stack.removeLast();

    /* 获取栈的长度 */
    int size = stack.size();

    /* 判断是否为空 */
    boolean isEmpty = stack.isEmpty();
    ```

=== "C++"

    ```cpp title="stack.cpp"
    /* 初始化栈 */
    stack<int> stack;

    /* 元素入栈 */
    stack.push(1);
    stack.push(3);
    stack.push(2);
    stack.push(5);
    stack.push(4);

    /* 访问栈顶元素 */
    int top = stack.top();

    /* 元素出栈 */
    stack.pop();

    /* 获取栈的长度 */
    int size = stack.size();

    /* 判断是否为空 */
    bool empty = stack.empty();
    ```

=== "Python"

    ```python title="stack.py"
    """ 初始化栈 """
    # Python 没有内置的栈类，可以把 List 当作栈来使用 
    stack = []

    """ 元素入栈 """
    stack.append(1)
    stack.append(3)
    stack.append(2)
    stack.append(5)
    stack.append(4)

    """ 访问栈顶元素 """
    peek = stack[-1]

    """ 元素出栈 """
    pop = stack.pop()

    """ 获取栈的长度 """
    size = len(stack)

    """ 判断是否为空 """
    is_empty = len(stack) == 0
    ```

=== "Go"

    ```go title="stack.go"
    /* 初始化栈 */
    // 在 Go 中，推荐将 Slice 当作栈来使用
    var stack []int

    /* 元素入栈 */
    stack = append(stack, 1)
    stack = append(stack, 3)
    stack = append(stack, 2)
    stack = append(stack, 5)
    stack = append(stack, 4)

    /* 访问栈顶元素 */
    peek := stack[len(stack)-1]

    /* 元素出栈 */
    pop := stack[len(stack)-1]
    stack = stack[:len(stack)-1]

    /* 获取栈的长度 */
    size := len(stack)

    /* 判断是否为空 */
    isEmpty := len(stack) == 0
    ```

=== "JavaScript"

    ```js title="stack.js"
    /* 初始化栈 */
    // Javascript 没有内置的栈类，可以把 Array 当作栈来使用 
    const stack = [];

    /* 元素入栈 */
    stack.push(1);
    stack.push(3);
    stack.push(2);
    stack.push(5);
    stack.push(4);

    /* 访问栈顶元素 */
    const peek = stack[stack.length-1];

    /* 元素出栈 */
    const pop = stack.pop();

    /* 获取栈的长度 */
    const size = stack.length;

    /* 判断是否为空 */
    const is_empty = stack.length === 0;
    ```

=== "TypeScript"

    ```typescript title="stack.ts"
    /* 初始化栈 */
    // Typescript 没有内置的栈类，可以把 Array 当作栈来使用 
    const stack: number[] = [];

    /* 元素入栈 */
    stack.push(1);
    stack.push(3);
    stack.push(2);
    stack.push(5);
    stack.push(4);

    /* 访问栈顶元素 */
    const peek = stack[stack.length - 1];

    /* 元素出栈 */
    const pop = stack.pop();

    /* 获取栈的长度 */
    const size = stack.length;

    /* 判断是否为空 */
    const is_empty = stack.length === 0;
    ```

=== "C"

    ```c title="stack.c"

    ```

=== "C#"

    ```csharp title="stack.cs"

    ```

## 栈的实现

为了更加清晰地了解栈的运行机制，接下来我们来自己动手实现一个栈类。

栈规定元素是先入后出的，因此我们只能在栈顶添加或删除元素。然而，数组或链表都可以在任意位置添加删除元素，因此 **栈可被看作是一种受约束的数组或链表**。换言之，我们可以 “屏蔽” 数组或链表的部分无关操作，使之对外的表现逻辑符合栈的规定即可。

### 基于链表的实现

使用「链表」实现栈时，将链表的尾结点看作栈顶即可。

受益于链表的离散存储方式，栈的扩容更加灵活，删除元素的内存也会被系统自动回收；缺点是无法像数组一样高效地随机访问，并且由于链表结点需存储指针，导致单个元素占用空间更大。

=== "Java"

    ```java title="linkedlist_stack.java"
    /* 基于链表实现的栈 */
    class LinkedListStack {
        private ListNode stackPeek;  // 将头结点作为栈顶
        private int stkSize = 0;   // 栈的长度
        
        public LinkedListStack() {
            stackPeek = null;
        }
        /* 获取栈的长度 */
        public int size() {
            return stkSize;
        }
        /* 判断栈是否为空 */
        public boolean isEmpty() {
            return size() == 0;
        }
        /* 入栈 */
        public void push(int num) {
            ListNode node = new ListNode(num);
            node.next = stackPeek;
            stackPeek = node;
            stkSize++;
        }
        /* 出栈 */
        public int pop() {
            int num = peek();
            stackPeek = stackPeek.next;
            stkSize--;
            return num;
        }
        /* 访问栈顶元素 */
        public int peek() {
            if (size() == 0)
                throw new IndexOutOfBoundsException();
            return stackPeek.val;
        }
    }
    ```

=== "C++"

    ```cpp title="linkedlist_stack.cpp"
    /* 基于链表实现的栈 */
    class LinkedListStack {
    private:
        ListNode* stackTop; // 将头结点作为栈顶
        int stkSize;        // 栈的长度

    public:
        LinkedListStack() {
            stackTop = nullptr;
            stkSize = 0;
        }
        /* 获取栈的长度 */
        int size() {
            return stkSize;
        }
        /* 判断栈是否为空 */
        bool empty() {
            return size() == 0;
        }
        /* 入栈 */
        void push(int num) {
            ListNode* node = new ListNode(num);
            node->next = stackTop;
            stackTop = node;
            stkSize++;
        }
        /* 出栈 */
        int pop() {
            int num = top();
            stackTop = stackTop->next;
            stkSize--;
            return num;
        }
        /* 访问栈顶元素 */
        int top() {
            if (size() == 0)
                throw out_of_range("栈为空");
            return stackTop->val;
        }
    };
    ```

=== "Python"

    ```python title="linkedlist_stack.py"
    """ 基于链表实现的栈 """
    class LinkedListStack:
        def __init__(self):
            self.__peek = None
            self.__size = 0

        """ 获取栈的长度 """
        def size(self):
            return self.__size

        """ 判断栈是否为空 """
        def is_empty(self):
            return not self.__peek

        """ 入栈 """
        def push(self, val):
            node = ListNode(val)
            node.next = self.__peek
            self.__peek = node
            self.__size += 1

        """ 出栈 """
        def pop(self):
            num = self.peek()
            self.__peek = self.__peek.next
            self.__size -= 1
            return num

        """ 访问栈顶元素 """
        def peek(self):
            # 判空处理
            if not self.__peek: return None
            return self.__peek.val
    ```

=== "Go"

    ```go title="linkedlist_stack.go"
    /* 基于链表实现的栈 */
    type LinkedListStack struct {
        // 使用内置包 list 来实现栈
        data *list.List
    }

    // NewLinkedListStack 初始化链表
    func NewLinkedListStack() *LinkedListStack {
        return &LinkedListStack{
            data: list.New(),
        }
    }

    // Push 入栈
    func (s *LinkedListStack) Push(value int) {
        s.data.PushBack(value)
    }

    // Pop 出栈
    func (s *LinkedListStack) Pop() any {
        if s.IsEmpty() {
            return nil
        }
        e := s.data.Back()
        s.data.Remove(e)
        return e.Value
    }

    // Peek 访问栈顶元素
    func (s *LinkedListStack) Peek() any {
        if s.IsEmpty() {
            return nil
        }
        e := s.data.Back()
        return e.Value
    }

    // Size 获取栈的长度
    func (s *LinkedListStack) Size() int {
        return s.data.Len()
    }

    // IsEmpty 判断栈是否为空
    func (s *LinkedListStack) IsEmpty() bool {
        return s.data.Len() == 0
    }
    ```

=== "JavaScript"

    ```js title="linkedlist_stack.js"

    ```

=== "TypeScript"

    ```typescript title="linkedlist_stack.ts"

    ```

=== "C"

    ```c title="linkedlist_stack.c"

    ```

=== "C#"

    ```csharp title="linkedlist_stack.cs"

    ```

### 基于数组的实现

使用「数组」实现栈时，将数组的尾部当作栈顶。准确地说，我们需要使用「列表」，因为入栈的元素可能是源源不断的，因此使用动态数组可以方便扩容。

基于数组实现的栈，优点是支持随机访问，缺点是会造成一定的空间浪费，因为列表的容量始终 $\geq$ 元素数量。

=== "Java"

    ```java title="array_stack.java"
    /* 基于数组实现的栈 */
    class ArrayStack {
        private ArrayList<Integer> stack;
        public ArrayStack() {
            // 初始化列表（动态数组）
            stack = new ArrayList<>();
        }
        /* 获取栈的长度 */
        public int size() {
            return stack.size();
        }
        /* 判断栈是否为空 */
        public boolean isEmpty() {
            return size() == 0;
        }
        /* 入栈 */
        public void push(int num) {
            stack.add(num);
        }
        /* 出栈 */
        public int pop() {
            return stack.remove(size() - 1);
        }
        /* 访问栈顶元素 */
        public int peek() {
            return stack.get(size() - 1);
        }
        /* 访问索引 index 处元素 */
        public int get(int index) {
            return stack.get(index);
        }
    }
    ```

=== "C++"

    ```cpp title="array_stack.cpp"
    /* 基于数组实现的栈 */
    class ArrayStack {
    private:
        vector<int> stack;
        
    public:
        /* 获取栈的长度 */
        int size() {
            return stack.size();
        }
        /* 判断栈是否为空 */
        bool empty() {
            return stack.empty();
        }
        /* 入栈 */
        void push(int num) {
            stack.push_back(num);
        }
        /* 出栈 */
        int pop() {
            int oldTop = stack.back();
            stack.pop_back();
            return oldTop;
        }
        /* 访问栈顶元素 */
        int top() {
            return stack.back();
        }
        /* 访问索引 index 处元素 */
        int get(int index) {
            return stack[index];
        }
    };
    ```

=== "Python"

    ```python title="array_stack.py"
    """ 基于数组实现的栈 """
    class ArrayStack:
        def __init__(self):
            self.__stack = []

        """ 获取栈的长度 """
        def size(self):
            return len(self.__stack)

        """ 判断栈是否为空 """
        def is_empty(self):
            return self.__stack == []

        """ 入栈 """
        def push(self, item):
            self.__stack.append(item)

        """ 出栈 """
        def pop(self):
            return self.__stack.pop()

        """ 访问栈顶元素 """
        def peek(self):
            return self.__stack[-1]

        """ 访问索引 index 处元素 """
        def get(self, index):
            return self.__stack[index]
    ```

=== "Go"

    ```go title="array_stack.go"
    /* 基于数组实现的栈 */
    type ArrayStack struct {
        data []int // 数据
    }

    func NewArrayStack() *ArrayStack {
        return &ArrayStack{
            // 设置栈的长度为 0，容量为 16
            data: make([]int, 0, 16),
        }
    }

    // Size 栈的长度
    func (s *ArrayStack) Size() int {
        return len(s.data)
    }

    // IsEmpty 栈是否为空
    func (s *ArrayStack) IsEmpty() bool {
        return s.Size() == 0
    }

    // Push 入栈
    func (s *ArrayStack) Push(v int) {
        // 切片会自动扩容
        s.data = append(s.data, v)
    }

    // Pop 出栈
    func (s *ArrayStack) Pop() any {
        // 弹出栈前，先判断是否为空
        if s.IsEmpty() {
            return nil
        }
        val := s.Peek()
        s.data = s.data[:len(s.data)-1]
        return val
    }

    // Peek 获取栈顶元素
    func (s *ArrayStack) Peek() any {
        if s.IsEmpty() {
            return nil
        }
        val := s.data[len(s.data)-1]
        return val
    }
    ```

=== "JavaScript"

    ```js title="array_stack.js"

    ```

=== "TypeScript"

    ```typescript title="array_stack.ts"

    ```

=== "C"

    ```c title="array_stack.c"

    ```

=== "C#"

    ```csharp title="array_stack.cs"

    ```

!!! tip

    实际编程中，我们一般直接将 `ArrayList` 或 `LinkedList` 当作「栈」来使用。我们仅需通过脑补来屏蔽无关操作，而不用专门去包装它。

## 栈典型应用

- **浏览器中的后退与前进、软件中的撤销与反撤销。** 每当我们打开新的网页，浏览器就讲上一个网页执行入栈，这样我们就可以通过「后退」操作来回到上一页面，后退操作实际上是在执行出栈。如果要同时支持后退和前进，那么则需要两个栈来配合实现。
- **程序内存管理。** 每当调用函数时，系统就会在栈顶添加一个栈帧，用来记录函数的上下文信息。在递归函数中，向下递推会不断执行入栈，向上回溯阶段时出栈。
