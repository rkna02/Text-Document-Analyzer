package cpen221.mp1.similarity;

import cpen221.mp1.Document;

import javax.print.Doc;
import java.util.HashSet;
import java.util.Set;

public class GroupingDocuments {

    /* ------- Task 5 ------- */

    /**
     * Group documents by similarity
     * @param allDocuments the set of all documents to be considered,
     *                     is not null
     * @param numberOfGroups the number of document groups to be generated
     * @return groups of documents, where each group (set) contains similar
     * documents following this rule: if D_i is in P_x, and P_x contains at
     * least one other document, then P_x contains some other D_j such that
     * the divergence between D_i and D_j is smaller than (or at most equal
     * to) the divergence between D_i and any document that is not in P_x.
     */
    public static Set<Set<Document>> groupBySimilarity(Set<Document> allDocuments, int numberOfGroups) {
        //built set of sets: <<Document1>,<Document2>,<Document3>...<DocumentN>>
        //check if count >= numberOfGroups
            //if yes, return set of sets
            //if not,...
                //1st: select closest pair of documents
                //2nd: check if these documents are already together
                     //if yes, go do line 1 again
                     //if not, merge the partitions with the two documents together

        DocumentSimilarity wtf = new DocumentSimilarity();

        HashSet<Set<Document>> setOfSets =new HashSet<Set<Document>>();
        for(Document i : allDocuments){
            HashSet<Document> innerSet = new HashSet<>();
            innerSet.add(i);
            setOfSets.add(innerSet);
        }
        int count = setOfSets.size();
        double best = 1000.0;
        double prevBest = 1000.0;
        Document a;
        Document b;
        Set<Document> setWitha = new HashSet<>();
        Set<Document> setWithb = new HashSet<>();

        for(Document i : allDocuments){
            for(Document j : allDocuments){
                if(i != j) {
                    if(wtf.documentDivergence(i,j) < best) {
                        best = wtf.documentDivergence(i,j);
                        a = i;
                        b = j;
                    }
                }
            }
        }

        prevBest = best;

        Set<Document> temp = new HashSet<>();
        temp.addAll(setWitha);
        temp.addAll(setWithb);
        setOfSets.add(temp);
        setOfSets.remove(setWitha);
        setOfSets.remove(setWithb);
        count--;
        setWithb.clear();
        setWitha.clear();

        while(count > numberOfGroups){

            while(setWitha.equals(setWithb)){
                for(Document i : allDocuments){
                for(Document j : allDocuments){
                    if(i != j) {
                        if(wtf.documentDivergence(i,j) < best && wtf.documentDivergence(i,j) > prevBest) {
                            best = wtf.documentDivergence(i,j);
                            a = i;
                            for(Set<Document> s : setOfSets) {
                                if(s.contains(a)) {
                                    setWitha = s;
                                }
                            }
                            b = j;
                            for(Set<Document> s : setOfSets) {
                                if(s.contains(b)) {
                                    setWithb = s;
                                }
                            }

                        }
                    }
                }
                }
                prevBest = best;
            }

            //set with a merge with set with b
            temp.clear();
            temp.addAll(setWitha);
            temp.addAll(setWithb);
            setOfSets.add(temp);
            setOfSets.remove(setWitha);
            setOfSets.remove(setWithb);
            count--;
            setWithb.clear();
            setWitha.clear();
            setWitha = setWithb;
            //clear them again
        }

        return setOfSets;

    }

}