package ml;

public class GeneticMain<T extends GeneticInterface>{

    T game;
    int brainCount;

    int inputSize, hiddenSize, outputSize;

    NeuralNetwork best;
    NeuralNetwork[] trainingSet;

    public GeneticMain(T game, int brainCount, int inputSize, int hiddenSize, int outputSize){
        this.game = game;
        this.brainCount = brainCount;

        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;

        //on setup les cerveaux
        trainingSet = new NeuralNetwork[brainCount];
        for(int i = 0; i < brainCount; i ++){
            trainingSet[i] = new NeuralNetwork(inputSize,hiddenSize,outputSize);
        }
        best = trainingSet[0];
    }

    public void itteration(float sigma){
        //etape 0 - on fait le score pour chaque cerveau
        int[] score = new int[brainCount];
        for(int i = 0 ; i < brainCount; i ++){
            game.reset();
            while(!game.isFinished()){
                float[] output = trainingSet[i].feedForward(game.getInput());
                game.play(output);
            }
            score[i] = game.getScore();
        }
        //etape 1 - on recupère le meilleur (indice)
        int bestScore = score[0];
        int bestI = 0;
        for(int i = 0 ; i < brainCount; i ++){
            if(score[i] > bestScore){
                bestScore = score[i];
                bestI = i;
            }
        }

        //etape 2 - on le copie partout
        for(int i = 0 ; i < brainCount; i ++){
            if(i != bestI){
                trainingSet[i].setWeights(trainingSet[bestI]);
            }
        }
        //etape 3 - on fait tout muter
        for(int i =1 ; i < brainCount; i ++){
            trainingSet[i].mutate(sigma);
        }
    }
}
