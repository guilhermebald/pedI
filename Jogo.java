public class Jogo {
    
    private String review;
    private String titulo;
    private String platform;
    private Double score;

    public Jogo(String titulo, String platform, Double score/*, String release*/) {
        this.titulo = titulo;
        this.platform = platform;
        this.score = score;

    }

    public Jogo() {
    
    }
    
    public Jogo(Jogo game) {
        all(game);
    }
    
    private void all(Jogo game) {
        this.titulo = game.getTitulo();
        this.platform = game.getPlatform();
        this.score = game.getScore();
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
  
    public void setPosicaoScore(char sinal, Jogo game){
        if(sinal == '+'){
            
            if(game.getScore() > this.getScore()){
                this.all(game);
            }
            
        } else if ( sinal == '-' ) {
            
            if(game.getScore() < this.getScore()){
                this.all(game);
            }
            
        } else {
            System.out.println("Sinal Inválido!");
        }
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
    
}
