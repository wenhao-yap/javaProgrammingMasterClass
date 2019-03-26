package com.justin.abstractClassChallenge;

public class CustomLinkedList {
  private ListItem head = null;

  //TODO:getAt(int index)
  public ListItem getAt(int idx){
    return findItemFromIndex(this.head,idx,0);
  }

  //TODO:remove(int index)
  public void remove(int index){
    ListItem removeItem = findItemFromIndex(this.head,index,0);
    ListItem previous = new Node(removeItem.previous().getValue());
    previous.setNext(removeItem.next());
    previous.next().setPrevious(previous);
    this.head = previous;
  }

  //TODO:insert(ListItem item)
  public void insert(Object value){
    if(value == null) return;
    ListItem newItem = new Node(value);
    ListItem current = this.head;

    if(current == null){
      this.head = newItem;
      return;
    }

    if(newItem.compareTo(current) == 0){
      System.out.println(newItem.getValue() + " already exist in list. Duplicates not allowed");
      return;
    }

    while(current.next() != null && newItem.compareTo(current) > 0){
      printCompare(current,newItem);
      current = current.next();
	}
    printCompare(current,newItem);
    ListItem previous = new Node(current.previous());
    previous.setNext(current);
    current.setNext(newItem);
    current.next().setPrevious(current);
    this.head = (current.previous() != null) ? current.previous() : current;
  }
  private void printCompare(ListItem current, ListItem newItem){
    System.out.println("Comparing " + current.getValue() + " and " + newItem.getValue() +
            " : " + current.compareTo(newItem));
    if(newItem.compareTo(current)>0){
      System.out.println(current.getValue() + "<" + newItem.getValue());
    } else if(newItem.compareTo(current) < 0){
      System.out.println(current.getValue() + ">" + newItem.getValue());
    }
  }

  //TODO: traverse
  public void traverse(){
    System.out.println("<--- Displaying List --->");
    ListItem current = this.head;
    int count = 0;
    while(current != null){
      Object previous = (current.previous() == null) ? null : current.previous().getValue();
      Object next = (current.next() == null) ? null : current.next().getValue();

      System.out.println(++count + ": " + current.getValue() + " [" + previous + "," + next + "]");
      current = current.next();
    }
    System.out.println("<--- End of List --->");
  }

  /**
   * TODO: find the index
   * @param currIdx : current idx count
   * @param idx : target idx
   * @param currItem: current item/root
   * */
  private ListItem findItemFromIndex(ListItem currItem, int idx, int currIdx) {
    if(currIdx < 0 || currIdx > idx) {
	  throw new ArrayIndexOutOfBoundsException();
	}
    if(currIdx == idx) return currItem;
	return findItemFromIndex(currItem.next(),idx,currIdx+=1);
  }
}