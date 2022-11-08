import java.io.DataInputStream;
import java.io.DataOutputStream;


class Player
{
	private String name;
	DataInputStream input;
	DataOutputStream output;
	
	
	public Player(String name) {
		this.name = name;
		}
	
	public String getName() {
		return name;
		}
	
	public void setInput(DataInputStream input) { 
		this.input = input;
		}
	
	public void setOutput(DataOutputStream output) {
		this.output = output;
		}
	
	public DataInputStream getInput() {
		return input;
		}
	
	public DataOutputStream getOutput() {
		return output;
		}
}