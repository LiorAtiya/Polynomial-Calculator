package M_4;

public class Monom {
	
	private double a;
	private int n;

	//Constructor
	Monom(double coeff, int power) {
		this.a = coeff;
		this.n = power;
	}

	//Copy Constructor
	Monom(Monom m){
		Monom temp = new Monom(m.a, m.n);
		this.a = temp.a;
		this.n = temp.n;
	}
	
	
	//Getters and Setters
	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public int getN() {
		return n;
	}

	//String Constructor - convert to Monom
	public Monom(String s){
		//Template : [ a * x^n ]
		if(s.contains("^") && s.contains("x")) {
			//Split to a and n
			String[] sArr = s.split("[x^]+");
			this.a = Double.parseDouble(sArr[0]);
			this.n = Integer.parseInt((sArr[1]));

		}else if(s.contains("x")) {
			//Template : [ x ]
			if(s.length() == 1) {
				this.a = 1;
				this.n = 1;
				////Template : [ -x ]
			}else if(s.charAt(s.indexOf('x')-1) == '-') {
				this.a = -1;
				this.n = 1;
				////Template : [ a * x ]
			}else {
				this.a = Double.parseDouble(s.substring(0,s.indexOf('x')));
				this.n = 1;
			}
		}else
			//Template : [ a ]
			this.a = Double.parseDouble(s);

	}

	//Check if the monom equal to zero
	public boolean isZero() {
		if(a == 0) return true;
		return false;
	}

	//Equal to another monom
	public boolean equals(Monom m) {
		if(this.a == m.a && this.n == m.n) return true;
		return false;
	}

	//Add monom
	public void add(Monom m) {
		Monom temp = new Monom(m.a, m.n);
		if(m.n == this.n) this.a += temp.a;
	}

	//Multiply monom
	public void multiply(Monom m) {
		this.a *= m.a;
		this.n += m.n;
	}

	//Calculate the value of f(x)
	public double f(double x) {
		double y = a * Math.pow(x, n);
		return y;
	}

	//Compare to another monom
	public int compareTo(Monom m) {
		if(this.n < m.n) return -1;
		else if(this.n > m.n) return 1;
		return 0;

	}

	//Compute a new Monom which is the derivative of this Monom
	public Monom derivative() {
		Monom derivative = new Monom(this.a,this.n);
		//If the power is positive
		if(this.n > 0) {
			derivative.a = this.n*this.a;
			derivative.n = this.n-1;
		}else { //The power is smaller or equal than zero
			derivative.a = 0;
			derivative.n = 0;
		}

		return derivative;
	}

	//Returns String representing the value of Monom
	public String toString() {
		return this.a+" * x^"+this.n;
	}
}