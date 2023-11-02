import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Players {
    // atributos
    private int id;
    private String name;
    private int height;
    private int weight;
    private String college;
    private int birth;
    private String city;
    private String state;

    // construtores
    public Players() {
        id = -1;
        name = "";
        height = -1;
        weight = -1;
        college = "";
        birth = -1;
        city = "";
        state = "";
    }

    // gets e sets
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCollege() {
        return college;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public int getBirth() {
        return birth;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void Read(String line){
        int[] comma = new int[7];
        int j = 0;
        if(line.charAt(line.length()- 1) == ','){
            this.state = "";
        }
        for(int i = 0; i<line.length(); i++){
            if(line.charAt(i) == ','){
                comma[j] = i;
                j++;
            }
        }
        this.id = Integer.parseInt(line.substring(0, comma[0]));
        this.name = line.substring(comma[0]+1, comma[1]);
        this.height = Integer.parseInt(line.substring(comma[1]+1, comma[2]));
        this.weight = Integer.parseInt(line.substring(comma[2]+1, comma[3]));
        this.college = line.substring(comma[3]+1, comma[4]);
        this.birth = Integer.parseInt(line.substring(comma[4]+1, comma[5]));
        this.city = line.substring(comma[5]+1, comma[6]); 
        this.state = line.substring(comma[6]+1, line.length());

    }

    public void Print(){
        System.out.print("[");
        if(this.id == -1){
            System.out.print("nao informado");
        }else{System.out.print(this.id);}
        System.out.print(" ## ");
        if(this.name.equals("")){
            System.out.print("nao informado");
        }else{System.out.print(this.name);}
        System.out.print(" ## ");
        if(this.height == -1){
            System.out.print("nao informado");
        }else{System.out.print(this.height);}
        System.out.print(" ## ");
        if(this.weight == -1){
            System.out.print("nao informado");
        }else{System.out.print(this.weight);}
        System.out.print(" ## ");
        if(this.birth == -1){
            System.out.print("nao informado");
        }else{System.out.print(this.birth);}
        System.out.print(" ## ");
        if(this.college.equals("zzzzz")){
            System.out.print("nao informado");
        }else{System.out.print(this.college);}
        System.out.print(" ## ");
        if(this.city.equals("")){
            System.out.print("nao informado");
        }else{System.out.print(this.city);}
        System.out.print(" ## ");
        if(this.state.equals("")){
            System.out.print("nao informado");
        }else{System.out.print(this.state);}
        System.out.println("]");
    }

    public Players Clone(Players p){
        Players clone = new Players();
        clone.id = p.id;
        clone.name = p.name;
        clone.height = p.height;
        clone.weight = p.weight;
        clone.college = p.college;
        clone.birth = p.birth;
        clone.city = p.city;
        clone.state = p.state;
        return clone;
    }
}

public class TP02Q13 {
    public static int MAX = 4000;
    public static int max = 600;
    //public static int r = 10;

    public static void intercalation(int l, int middle, int r, Players[] p){
        int n1, n2, i, j, k;

        n1 = middle - l + 1;
        n2 = r - middle;

        Players[] a1 = new Players[n1+1];
        Players[] a2 = new Players[n2+1];

        for(i = 0; i<n1; i++){
            a1[i] = p[l+i];
            //a1[i].Print();
        }

        for(j = 0; j<n2; j++){
            a2[j] = p [middle + j + 1];
            //a2[j].Print();
        }


        for (i = j = 0, k = l; k <= r; k++) {
            /*if (i < n1 && (j >= n2 || (a1[i].getCollege().compareTo(a2[j].getCollege()) <= 0) || ((a1[i].getCollege().compareTo(a2[j].getCollege()) == 0) && (a1[i].getName().compareTo(a2[j].getName()) <= 0)))) {
                p[k] = a1[i++];
            } else {
                p[k] = a2[j++];
            }*/
      
            if (i < n1 && (j >= n2 || (a1[i].getCollege().compareTo(a2[j].getCollege()) < 0) 
                || (a1[i].getCollege().compareTo(a2[j].getCollege()) == 0 && a1[i].getName().compareTo(a2[j].getName()) < 0)
                || (a1[i].getCollege().compareTo(a2[j].getCollege()) == 0 && a1[i].getName().compareTo(a2[j].getName()) == 0 && a1[i].getId() < a2[j].getId()))) {
                p[k] = a1[i++];
            } else {
                p[k] = a2[j++];
            }
        }
        
    }

    public static void mergesort(int l, int r, Players[] p){
        if(l<r){
            int middle = (l+r)/2;
            mergesort(l, middle, p);
            mergesort(middle+1, r, p);
            intercalation(l, middle, r, p);
        }
    }


    public static void main(String[] args) { 
        Players[] players = new Players[MAX];
        Players[] merge = new Players[MAX];
       
        try {

            MyIO.setCharset("ISO-8859-1");

            BufferedReader arq = new BufferedReader(new FileReader("/tmp/players.csv")); //colocar tmp para entregar no verde
            arq.readLine();
            int i = 0;
            while(arq.ready()){
                players[i] = new Players(); 
                players[i].Read(arq.readLine()); 
                i++;
            }
            arq.close();

            /*-----------ENTRADA------------------ */
            String entry = new String();
            entry = MyIO.readLine();
            int entry_position = 0;
            while(!(entry != null && entry.equals("FIM"))){
                if(entry != null && entry.equals("FIM")){//sem esse if, tenta converter o fim e o programa termina com NumberFormatException
                    break;
                }
                int j = Integer.parseInt(entry);
                if(entry_position<merge.length){
                    merge[entry_position] = new Players();
                    merge[entry_position].setId(players[j].getId());
                    merge[entry_position].setName(players[j].getName());
                    merge[entry_position].setHeight(players[j].getHeight());
                    merge[entry_position].setWeight(players[j].getWeight());
                    merge[entry_position].setCollege(players[j].getCollege());
                    merge[entry_position].setBirth(players[j].getBirth());
                    merge[entry_position].setCity(players[j].getCity());
                    merge[entry_position].setState(players[j].getState());
                    entry_position++;
                }
                entry = MyIO.readLine();
            }

            for(int b = 0; b<entry_position; b++){
                if(merge[b].getCollege().equals("")){
                    merge[b].setCollege("zzzzz");
                }
            }

            mergesort(0, entry_position-1, merge);

            /*------------------------------------------------------ */

            for(int k = 0; k<entry_position; k++){
                merge[k].Print();
            }


        } catch (IOException read) { 
            System.out.println("Erro " + read);
        } catch (StringIndexOutOfBoundsException s) { 
            System.out.println("O índice está além do tamanho da string " + s);
        } catch (NullPointerException n) { 
            System.out.println("Ponteiro aponta para endereço inválido " + n);
        } catch (ArrayIndexOutOfBoundsException a) { // index out of the lenght of the archive
            System.out.println("O array não possui tal índice " + a);
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo " + e);
        }

    }
}
