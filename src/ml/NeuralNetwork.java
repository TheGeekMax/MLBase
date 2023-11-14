package ml;

import matriceTool.*;

public class NeuralNetwork<T extends GeneticInterface> {
    Matrice weightIH, weightIO;
    int inputSize, hiddenSize, outputSize;
    T game;


    public NeuralNetwork(int intputSize, int hiddenSize, int outputSize,T game){
        weightIH = new Matrice(hiddenSize,intputSize);
        weightIO = new Matrice(outputSize,hiddenSize);
        mutate(1f);
        this.inputSize = intputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;
        this.game = game;
    }

    public void mutate(float sigma){
        weightIH.mutate(sigma);
        weightIO.mutate(sigma);
    }

    public float[] feedForward(float[] inputArray){
        if(inputArray.length != inputSize){
            System.err.println("Array are not at same size !");
            return null;
        }
        Function act = NeuralNetwork::ReLU;

        Matrice input = new Matrice(inputSize,1);

        for(int i =0; i < inputSize; i ++){
            input.setElement(i,0,inputArray[i]);
        }

        Matrice hidden = weightIH.mult(input);
        hidden.apply(act);
        Matrice output = weightIO.mult(hidden);
        float[] outFinal = new float[outputSize];

        for(int i =0; i < outputSize; i ++){
            outFinal[i] = output.getElmement(i,0);
        }

        return outFinal;
    }

    public static float ReLU(float x){
        return Math.max(x,0);
    }
}
