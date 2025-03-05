//animal class
public class Animal {
	//animal name list
	private static String[] animalList= {
			"alligator","alpaca","anteater","antelope","ape","bat","bee","buffalo","camel","cat","chicken","cow","crow","dog",
			"dove","duck","elephant","falcon","ferret","frog","giraffe","guinea_pig","hedgehog","hippopotamus","horse","hyena",
			"komodo_dragon","leopard","lion","lizard","moose","otter","owl","panda","parrot","penguin","pig","rabbit","raccoon",
			"rat","rattlesnake","rhinoceros","robin","sheep","swan","tiger","turkey","whale","wolf","yak","zebra"};
	//animal cry list
	private static String[] cryList= {
			"hiss or bellow","hum","low grunting or soft snuffling","snort or bellow","hoot or screech","squeak or chirp","buzz",
			"grunt or bellow","grunt or moan","meow","cluck","moo","caw","bark or woof","coo","quack","trumpet","screech or caw","dook or hiss",
			"croak","low bleat or snort","squeak or whistle","snuffle or hiss","grunt or roar","neigh or whinny","laugh or howl","hiss or growl",
			"growl or snarl","roar","hiss or chirp","bellow or grunt","chirp or whistle","hoot","bleat or growl","squawk or mimic sounds",
			"bray or honk","oink","squeak or thump","chitter or growl","squeak or squeal","rattle or hiss","snort or grunt","chirp or sing",
			"baa or bleat","trumpet or hiss","roar or growl","gobble","sing or call","howl or growl","grunt or bellow","bray or whinny"};
	private String cry;
	private String name;
	private String imageFilepath;
	private String soundFilepath;
	
	
	public Animal(int i) {
		setName(animalList[i]);
		setCry(cryList[i]);
		setImage(animalList[i]);
		setSound(animalList[i]);
	}
	public Animal() {
	}
	
	//cry, name, sound file path, image file path set method
	private void setCry(String cry)
	{
		this.cry=cry;
	}
	private void setName(String animalName)
	{
		name=animalName;
	}
	private void setSound(String animalName)
	{
		soundFilepath="/resources/animal/sound/" + animalName + ".wav";
	}
	private void setImage(String animalName)
	{
		imageFilepath="/resources/animal/image/" + animalName + ".png";
	}
	
	//cry, name, sound file path, image file path get method
	public String getCry()
	{
		return cry;
	}
	public String getName()
	{
		return name;
	}
	public String getSound()
	{
		return soundFilepath;
	}
	public String getImage()
	{
		return imageFilepath;
	}
}

//animal array making class
class AnimalArr {
	private static Animal[] animalArr=new Animal[51];
	
	public AnimalArr() {
		for (int i=0;i<51;++i) {
			animalArr[i]=new Animal(i);
		}
	}
	
	//get Animal class of i index
	public Animal getAnimal(int i) {
		return animalArr[i];
	}
}