package org.tila.tree;

import java.util.List;

public class ParseTree {
    public ParseTree parent;

    public List<ParseTree> childs;

    public ParseTree getParent() {
        return parent;
    }

    public void setParent(ParseTree parent) {
        this.parent = parent;
    }

    public List<ParseTree> getChilds() {
        return childs;
    }

    public void setChilds(List<ParseTree> childs) {
        this.childs = childs;
    }

    public ParseTree getAChild(int i) {
        return this.childs.get(i);
    }

    public void addAChild(ParseTree aChild) {
        this.childs.add(aChild);
    }


    public int getNumberOfChild() {
        if (this.childs == null) {
            return 0;
        }
        return this.childs.size();

    }

    public String toString() {
        if (this.getNumberOfChild() == 0) {
            return "";
        }
        String result = "";
        result += "( ";
        result += this.getClass().getSimpleName();
        result += "( ";
        for (int i = 0; i < this.getNumberOfChild(); i++) {
            result += " " + this.getAChild(i).toString() + " ";
        }
        result += " )";
        result += " )";
        return result;
    }
}
