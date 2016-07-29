package gui.t3h.com.ailatrieuphu.model;

/**
 * Created by duyti on 7/16/2016.
 */
public class Question {
    private String nameQuestion, caseA, caseB, caseC, caseD;
    private int level,trueCase;

    public Question(String nameQuestion, String caseA, String caseB, String caseC, String caseD, int level, int trueCase) {
        this.nameQuestion = nameQuestion;
        this.caseA = caseA;
        this.caseB = caseB;
        this.caseC = caseC;
        this.caseD = caseD;
        this.level = level;
        this.trueCase = trueCase;
    }

    @Override
    public String toString() {
        return nameQuestion+"\n"+
                "level"+level+"\n"+
                "A:"+caseA+"\n"+
                "B:"+caseB+"\n"+
                "C:"+caseC+"\n"+
                "D:"+caseD+"\n"+
                "Answer:"+trueCase+"\n";
    }

    public String getNameQuestion() {
        return nameQuestion;
    }

    public String getCaseA() {
        return caseA;
    }

    public String getCaseB() {
        return caseB;
    }

    public String getCaseC() {
        return caseC;
    }

    public String getCaseD() {
        return caseD;
    }

    public int getLevel() {
        return level;
    }

    public int getTrueCase() {
        return trueCase;
    }
}
