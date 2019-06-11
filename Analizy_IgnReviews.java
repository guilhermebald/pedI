
import java.util.ArrayList;
import java.util.TreeMap;


public class Analizy_IgnReviews {

    public static void main(String[] args) {
        ArquivoLeitura log = new ArquivoLeitura("ign-reviews.csv");
        String linha = log.lerLinha();
        linha = log.lerLinha(); //deve-se pular a primeira linha pois ela contem o cabecalho
        String[] rowTable;

        TreeMap<String, Item> tableOne = new TreeMap();

        Item item;
        while (linha != null) {
            rowTable = linha.split(";");
            item = new Item();

            if (tableOne.containsKey(rowTable[6])) {
                //pega o item do dicionario
                item = tableOne.get(rowTable[6]);
                //coloca o item no dicionario
                tableOne.put(rowTable[6], popular(item, rowTable));
            } else {
                //coloca o item no dicionario
                tableOne.put(rowTable[6], popular(item, rowTable));
            }
            linha = log.lerLinha();
        }
        log.fechar();
        
        totalReviewsPorGenero(tableOne);
        totalReviewsCriterio(tableOne, "Masterpiece");
        percentualReviewVsAll(tableOne, "Amazing");
        
        System.out.println("\nMédia dos Score = " + mediaScore(tableOne));
        desvioPadrao(tableOne);
        bestAndWorstGame(tableOne);
        
    }

    public static Item popular(Item i, String[] array) {
        Jogo jogo = new Jogo();
        //preenche a classe com a nova linha
        jogo.setReview(array[1]);
        jogo.setTitulo(array[2]);
        jogo.setPlatform(array[4]);
        jogo.setScore(Double.parseDouble(array[5]));
        //insere o jogo dentro do item
        i.setInfos(jogo);
        return i;
    }
    
    public static void totalReviewsPorGenero(TreeMap<String, Item> table){
        System.out.println("\n\tNumero de reviews por genero");
        int cont = 0;
        for (String k  : table.keySet() ) {
            cont++;
            System.out.println(cont + "\t" + k + " = " + table.get(k).getInfos().size());
        }
    }
    
    public static void totalReviewsCriterio(TreeMap<String, Item> table, String criterio){
        System.out.println("\n\tNumero de '" + criterio + "'reviews");
        int cont = 0;
        for (String k  : table.keySet() ) {
            for(int i = 0; i < table.get(k).getInfos().size(); i++ ){
                if(table.get(k).getInfos().get(i).getReview().equals(criterio)){
                    cont++;
                }
            }
        }
        System.out.println("Resultado:\t" + criterio + " = " + cont);
    }
    
    public static void percentualReviewVsAll(TreeMap<String, Item> table, String criterio){
        System.out.println("\n\tPercentual de '" + criterio + "'");
        int cont = 0;
        int all = 0;
        for (String k  : table.keySet() ) {
            for(int i = 0; i < table.get(k).getInfos().size(); i++ ){
                if(table.get(k).getInfos().get(i).getReview().equals(criterio)){
                    cont++;
                }
            }
            all += table.get(k).getInfos().size();
        }
        
        Double percentual = (cont * (100.0/all));
        System.out.println("Resultado:\t" + criterio + " = " + percentual + " %" );
       
        
    }
    
    public static Double mediaScore(TreeMap<String, Item> table){
//        System.out.println("\n\tMédia dos Score");
        int cont = 0;
        Double score = 0.0;
        for (String k  : table.keySet() ) {
            for(int i = 0; i < table.get(k).getInfos().size(); i++ ){
                score += table.get(k).getInfos().get(i).getScore();
                cont++;
            }
        }
        return score / cont;
    }
    
    public static void desvioPadrao(TreeMap<String, Item> table){
        System.out.println("\n\tDesvio padrão dos Scores:");
        ArrayList<Double> v = new ArrayList();
        Double media = mediaScore(table);
        Double temp;
        for (String k  : table.keySet() ) {
            for(int i = 0; i < table.get(k).getInfos().size(); i++ ){
                temp = ((table.get(k).getInfos().get(i).getScore()) - media) * ((table.get(k).getInfos().get(i).getScore()) - media); 
                v.add(temp);
            }
        }
        Double variancia  = 0.0;
        for(int i = 0; i < v.size(); i++){
            variancia += v.get(i);
        }
        
        variancia = variancia/(v.size()-1);
        
        System.out.println("Desvio Padrão = \t" + Math.sqrt(variancia));
    }
    
    
    public static void bestAndWorstGame(TreeMap<String, Item> table){
        System.out.println("\n\tMelhor e Pior Jogo:");
        Jogo bestGame = new Jogo();
        bestGame.setScore(0.0);
        Jogo worstGame = new Jogo();
        worstGame.setScore(10.0);
        int cont = 0;
        for (String k  : table.keySet() ) {
            for(int i = 0; i < table.get(k).getInfos().size(); i++ ){
                bestGame.setPosicaoScore( '+', table.get(k).getInfos().get(i) );
                worstGame.setPosicaoScore( '-', table.get(k).getInfos().get(i) );
            }
        }
        
        System.out.println("Melhor jogo =>\t" + bestGame.getTitulo() + " - " + bestGame.getPlatform() + " - " + bestGame.getScore() );
        System.out.println("Pior Jogo   =>\t" + worstGame.getTitulo() + " - " + worstGame.getPlatform() + " - " + worstGame.getScore());
        
    }
    
    
}
