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
        if(this.college.equals("")){
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

public class questao9 {
    public static int MAX = 4000;
    public static int max = 600;
    public static int r = 10;

    public static void construct(int heap_size, Players[] p) {
        for (int i = heap_size; i > 1 && ((p[i].getHeight() > p[i / 2].getHeight()) || 
                (p[i].getHeight() == p[i / 2].getHeight() && p[i].getName().compareTo(p[i / 2].getName()) > 0)); i /= 2) {
            Players tmp = p[i];
            p[i] = p[i / 2];
            p[i / 2] = tmp;
        }
    }

    public static void reconstruct(int heap_size, Players[] p) {
        int i = 1;
        while (i <= heap_size / 2) {
            int index = 2 * i;
            if (index < heap_size && (p[index].getHeight() < p[index + 1].getHeight() || 
                    (p[index].getHeight() == p[index + 1].getHeight() && p[index].getName().compareTo(p[index + 1].getName()) < 0))) {
                index++;
            }
            if (p[i].getHeight() < p[index].getHeight() || 
                    (p[i].getHeight() == p[index].getHeight() && p[i].getName().compareTo(p[index].getName()) < 0)) {
                Players tmp = p[i];
                p[i] = p[index];
                p[index] = tmp;
                i = index;
            } else {
                break;
            }
        }
    }

    public static void heapsort(Players[] p, int entry_position) {
        Players[] tmp = new Players[entry_position + 1];
        for (int i = 0; i < entry_position; i++) {
            tmp[i + 1] = p[i];
        }
        p = tmp;
        for (int heap_size = 2; heap_size <= entry_position; heap_size++) {
            construct(heap_size, p);
        }
        int heap_size = entry_position;
        while (heap_size > 1) {
            Players temp = p[1];
            p[1] = p[heap_size];
            p[heap_size] = temp;
            heap_size--;
            reconstruct(heap_size, p);
        }
        tmp = p;
        p = new Players[entry_position];
        for (int i = 0; i < entry_position; i++) {
            p[i] = tmp[i + 1];
        }
    }

    public static void main(String[] args) {
        Players[] players = new Players[MAX];
        Players[] heap_players = new Players[MAX];



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
                if(entry_position<heap_players.length){
                    heap_players[entry_position] = new Players();
                    heap_players[entry_position].setId(players[j].getId());
                    heap_players[entry_position].setName(players[j].getName());
                    heap_players[entry_position].setHeight(players[j].getHeight());
                    heap_players[entry_position].setWeight(players[j].getWeight());
                    heap_players[entry_position].setCollege(players[j].getCollege());
                    heap_players[entry_position].setBirth(players[j].getBirth());
                    heap_players[entry_position].setCity(players[j].getCity());
                    heap_players[entry_position].setState(players[j].getState());
                    entry_position++;
                }
                entry = MyIO.readLine();
            }

            heapsort(heap_players, entry_position);

            for (int k = 0; k < entry_position; k++) {
                heap_players[k].Print();
            }
            
        } catch (IOException read) {
            System.out.println("Erro " + read);
        } catch (StringIndexOutOfBoundsException s) {
            System.out.println("O índice está além do tamanho da string " + s);
        } catch (NullPointerException n) {
            System.out.println("Ponteiro aponta para endereço inválido " + n);
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("O array não possui tal índice " + a);
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo " + e);
        }
    }
}
