package ml;

import matriceTool.*;

public class NeuralNetwork {
    Matrice weightIH, weightHO;
    int inputSize, hiddenSize, outputSize;


    public NeuralNetwork(int inputSize, int hiddenSize, int outputSize){
        weightIH = new Matrice(hiddenSize,inputSize);
        weightHO = new Matrice(outputSize,hiddenSize);
        mutate(1f);
        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;
    }

    public void mutate(float sigma){
        weightIH.mutate(sigma);
        weightHO.mutate(sigma);
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
        Matrice output = weightHO.mult(hidden);
        float[] outFinal = new float[outputSize];

        for(int i =0; i < outputSize; i ++){
            outFinal[i] = output.getElmement(i,0);
        }

        return outFinal;
    }

    public void setWeights(Matrice IH, Matrice HO){
        weightIH.set(IH);
        weightHO.set(HO);
    }

    public void setWeights(NeuralNetwork reference){
        setWeights(reference.getWeightIH(),reference.getWeightHO());
    }

    public Matrice getWeightIH(){
        return weightIH;
    }

    public Matrice getWeightHO(){
        return weightHO;
    }

    public static float ReLU(float x){
        return Math.max(x,0);
    }
}
