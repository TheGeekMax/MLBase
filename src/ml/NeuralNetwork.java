package ml;

import matriceTool.Matrice;

public class NeuralNetwork<T extends GeneticInterface> {
    Matrice weightIH, weightIO;
    int inputSize, hiddenSize, outputSize;
    public NeuralNetwork(int intputSize, int hiddenSize, int outputSize){
        weightIH = new Matrice(hiddenSize,intputSize);
        weightIO = new Matrice(outputSize,hiddenSize);
        this.inputSize = intputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;
    }


}
