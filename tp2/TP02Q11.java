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
        if(this.state.equals("zzzzz")){
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

public class TP02Q11 {
    public static int MAX = 4000;
    public static int max = 600;
    public static int a = 10;

    public static Players getMaior(Players[] array, int size) {
        Players maior = new Players();
        maior = array[0];
 
        for (int i = 0; i < size; i++) {
            if(maior.getHeight() < array[i].getHeight()){
                maior = array[i];
            }
        }
        return maior;	
    }

    public static void sort(Players[] array, int size) {
        int[] count = new int[getMaior(array, size).getHeight() + 1];
        Players[] ordenado = new Players[size];
  
        for (int i = 0; i < count.length; count[i] = 0, i++);
  
        //Agora, o count[i] contem o numero de elemento iguais a i
        for (int i = 0; i < size; count[array[i].getHeight()]++, i++);
  
        //Agora, o count[i] contem o numero de elemento menores ou iguais a i
        for(int i = 1; i < count.length; count[i] += count[i-1], i++);
  
        //Ordenando
        for(int i = size-1; i >= 0; ordenado[count[array[i].getHeight()]-1] = array[i], count[array[i].getHeight()]--, i--);
  
        //Copiando para o array original
        for(int i = 0; i < size; array[i] = ordenado[i], i++);
    }

    public static void main(String[] args) { 
        Players[] players = new Players[MAX];
        Players[] counting = new Players[MAX];
       
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
                if(entry_position<counting.length){
                    counting[entry_position] = new Players();
                    counting[entry_position].setId(players[j].getId());
                    counting[entry_position].setName(players[j].getName());
                    counting[entry_position].setHeight(players[j].getHeight());
                    counting[entry_position].setWeight(players[j].getWeight());
                    counting[entry_position].setCollege(players[j].getCollege());
                    counting[entry_position].setBirth(players[j].getBirth());
                    counting[entry_position].setCity(players[j].getCity());
                    counting[entry_position].setState(players[j].getState());
                    entry_position++;
                }
                entry = MyIO.readLine();
            }

            sort(counting, entry_position);

            

            for(int a = 0; a<entry_position; a++){
                for(int b = 1; b<entry_position-1; b++){
                    if((counting[a].getHeight() == counting[b].getHeight()) && counting[a].getName().compareTo(counting[b].getName())<0){
                        Players tmp = new Players();
                        tmp = counting[a];
                        counting[a] = counting[b];
                        counting[b] = tmp;
                    }
                }
            }

            for(int k = 0; k<entry_position; k++){
                counting[k].Print();
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
