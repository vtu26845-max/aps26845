class MyCircularDeque {
    int data[];
    int front ;
    int last;
    int n;
    int size;
    public MyCircularDeque(int k) {
        data = new int[k];
        size = k;
        n = 0;
        front = last = -1;
    }
    public boolean insertFront(int value) {
        if(isFull()) return false;
        if(front == -1) {
            front = last = 0;
        } else front = ((front - 1 + size) % size);   
        data[front] = value;
        n++;
        return true;
    }
    public boolean insertLast(int value) {
        if(isFull()) return false;
        if(last == -1) front = last = 0;
        else last = (last + 1) % size;
        data[last] = value;
        n++;
        return true;
    }
    public boolean deleteFront() {
        if(isEmpty()) return false;
        front = ((front + 1) % size );
        n--;
        return true;
    }
    public boolean deleteLast() {
        if(isEmpty()) return false;
        last = ((last - 1 + size) % size);
        n--;
        return true;
    }
    public int getFront() {
        if(isEmpty()) return -1;
        int val = data[front];
        return val;
    }
    public int getRear() {
        if(isEmpty()) return -1;
        int val = data[last];
        return val;
    }
    public boolean isEmpty() {
        if(n == 0) return true;
        return false;
    }   
    public boolean isFull() {
        if(n == size) return true;
        return false;
    }
}