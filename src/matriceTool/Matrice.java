package matriceTool;

public class Matrice {

    private float[][] mat;
    private int m,n;

    public Matrice(int m, int n){
        mat = new float[m][n];
        this.m = m;
        this.n = n;

        for(int i = 0 ; i < m; i ++){
            for(int j = 0 ; j < n; j ++){
                mat[i][j] = 0;
            }
        }
    }

    public Matrice add(Matrice other){
        if (m != other.getLineCount() || n != other.getCollumnCount()){
            System.err.println("Matrices are not at same size !");
            return null;
        }

        Matrice added = new Matrice(m,n);
        for(int i = 0 ; i < m; i ++){
            for(int j = 0 ; j < n; j ++){
                added.setElement(i,j,mat[i][j] + other.getElmement(i,j));
            }
        }

        return added;
    }

    public Matrice mult(Matrice other){
        if (n != other.getLineCount()){
            System.err.println("Matrices are not at good size !");
            return null;
        }

        Matrice multed = new Matrice(m,other.getCollumnCount());
        for(int i = 0 ; i < m; i ++){
            for(int j = 0 ; j < other.getCollumnCount(); j ++){
                int coef = 0;
                for(int itt = 0 ; itt < n; itt++){
                    coef += mat[i][itt] * other.getElmement(itt,j);
                }
                multed.setElement(i,j,coef);
            }
        }

        return multed;
    }

    public void set(Matrice other){
        if (m != other.getLineCount() || n != other.getCollumnCount()){
            System.err.println("Matrices are not at same size !");
            return;
        }

        for(int i = 0 ; i < m; i ++){
            for(int j = 0 ; j < n; j ++){
                mat[i][j] = other.getElmement(i,j);
            }
        }
    }

    public void mutate(float sigma){
        for(int i = 0 ; i < m; i ++){
            for(int j = 0 ; j < n; j ++){
                mat[i][j] += (float) ((Math.random()*2*sigma)-sigma);
            }
        }
    }

    public void apply(Function func){
        for(int i = 0 ; i < m; i ++){
            for(int j = 0 ; j < n; j ++){
                mat[i][j] = func.apply(mat[i][j]);
            }
        }
    }

    public int getLineCount(){
        return m;
    }

    public int getCollumnCount(){
        return n;
    }

    public float getElmement(int l, int m){
        return mat[l][m];
    }

    public void setElement(int l,int m, float val){
        mat[l][m] = val;
    }

    public void print(){
        for(int i = 0 ; i < m; i ++){
            for(int j = 0 ; j < n; j ++){
                System.out.print(" - "+mat[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
