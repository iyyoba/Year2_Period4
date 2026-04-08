package builder;

class GamingComputerBuilder implements ComputerBuilder {
    private Computer computer;

    public GamingComputerBuilder() {
        this.computer = new Computer();
    }

    public void buildProcessor() {
        computer.setProcessor("Intel Core i9");
    }

    public void buildRAM() {
        computer.setRAM(32);
    }

    public void buildHardDrive() {
        computer.setHardDrive("1 TB SSD");
    }

    public void buildGraphicsCard() {
        computer.setGraphicsCard("NVIDIA RTX 4090");
    }

    public void buildOperatingSystem() {
        computer.setOperatingSystem("Windows 11");
    }

    public Computer getComputer() {
        return computer;
    }
}