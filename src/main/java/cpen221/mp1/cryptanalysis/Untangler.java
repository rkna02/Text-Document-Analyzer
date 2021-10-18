package cpen221.mp1.cryptanalysis;

public abstract class Untangler {

    /**
     * Determine if {@code superposition} is a result of
     * a tangling of {@code src1} and {@code src2}.
     *
     * @param superposition the possibly tangled signal, not null
     * @param src1 the first signal, not null
     * @param src2 the second signal, not null
     * @return true is {@code superposition} is a
     * tangling of {@code src1} and {@code src2} and false otherwise.
     */
    public static boolean areTangled(String superposition, String src1, String src2) {

        if(superposition.length() == 0) { //break condition, also prevents empty string reading
            return true;
        }

        if (superposition.charAt(0) == src1.charAt(0))
        {
            StringBuilder xp = new StringBuilder(); //rotate x by one
            xp.append(src1.substring(1));
            xp.append(src1.charAt(0));
            if (areTangled(superposition.substring(1),xp.toString(),src2))
                return true;
        }
        if (superposition.charAt(0) == src2.charAt(0))
        {
            StringBuilder yp = new StringBuilder(); //rotate y by one
            yp.append(src2.substring(1));
            yp.append(src2.charAt(0));
            if (areTangled(superposition.substring(1),src1,yp.toString()))
                return true;
        }

        return false;
    }





}