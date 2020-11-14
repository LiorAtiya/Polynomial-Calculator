package M_4;

import java.util.ArrayList;

public class Polynom {

	private ArrayList<Monom> arr;

	//Constructor
	Polynom(){
		this.arr = new ArrayList<>();
	}

	//Copy constructor
	Polynom(Polynom p){
		this.arr = new ArrayList<>();

		for(int i=0 ; i < p.arr.size() ; i++) {
			arr.add(p.arr.get(i));
		}

	}

	//Add Monom.
	public void add(Monom m) {
		Monom temp = new Monom(m);

		//If the monom is equal to zero.
		if(m.isZero()) return;

		else {
			//If the arraylist is empty.
			if(arr.size() == 0) {
				arr.add(temp);
				return;

			} else {
				for(int i=0 ; i < arr.size() ; i++) {
					//Check the power of Monom.
					if(this.arr.get(i).getN() == m.getN()) {
						this.arr.get(i).add(temp); //Add to exists.

						//If a monom array exists that is zero
						for(int k = 0 ; k < this.arr.size() ; k++) {
							if(this.arr.get(i).isZero()) this.arr.remove(i);
						}

						return;
					}
				}

				//Adds the monon after the smaller ones
				for(int j=0 ; j < arr.size() ; j++) {
					if(arr.get(j).compareTo(temp) == -1) {
						arr.add(j,temp); //Add new Monom.

						return;
					}
				}
				//The smallest power of Monom and different from all other Monom
				//Add at the end.
				arr.add(temp);
			}
		}

	}

	//Add polynom that contain arraylist of monom.
	public void add(Polynom p) {
		for(int i=0 ; i < p.arr.size() ; i++) {
			this.add(p.arr.get(i));
		}
	}

	//Compute Riemann's Integral over this Polynom starting from x0 till x1 
	public double area(double x0, double x1, double eps) {
		double area = 0;

		while(x0 <= x1) {
			//Computes small rectangles and connects them
			double rectangle = f(x0) * eps;
			area += rectangle;
			x0 = x0 + eps;
		}

		return area;

	}

	//Compute a new Polynom which is the derivative of this Polynom
	public Polynom derivative() {
		Polynom temp = new Polynom(this);

		//A derivative of each Monom from the arr
		for(int i=0 ; i < arr.size(); i++) {
			temp.arr.set(i, temp.arr.get(i).derivative());
		}

		return temp;
	}

	//Test if this logically equals to p
	public boolean equals(Polynom p) {
		for(int i=0 ; i < this.arr.size() ; i++) {
			if(!p.arr.get(i).equals(arr.get(i))) return false;
		}
		return true;
	}

	//Tests if this is a zero Polynom (all monoms are zero)
	public boolean isZero() {
		for(int i=0 ; i < arr.size() ; i++) {
			if(!arr.get(i).isZero()) return false;
		}
		return true;
	}

	//Multiply this Polynom by p
	public void multiply(Polynom p) {
		Polynom temp = new Polynom();

		for(int i=0 ; i < arr.size() ; i++) {
			for(int j=0 ; j < p.arr.size() ; j++) {

				//Multiplies each monom in arr by the other
				//monoms of the polynom p and stores them in temp
				Monom t = new Monom(this.arr.get(i));
				this.arr.get(i).multiply(p.arr.get(j));
				temp.add(this.arr.get(i));
				this.arr.set(i, t);
			}
		}
		this.arr = temp.arr;
	}

	//Compute a value x: x0 <= x <= x1 for whitch |f(x)|<eps
	public double root(double x0, double x1, double eps) {

		double y0 = f(x0);
		double y1 = f(x1);

		//Taking the middle between the two points 
		//And calculates the value of f(x)
		double middle=(x0+x1)/2;
		double yMiddle = f(middle);


		//If there is no intersection with an x-axis between x0 & x1
		if(y0 * y1 > 0) {
			System.out.println("No root");
			return -1;
		}else {	
			double absY = Math.abs((yMiddle));

			//Decreases the range of the values f(x) of x0 & x1 
			//until it is close to meeting with the x axis
			while(absY > eps) {
				if(yMiddle==0) 
					return middle;
				else if(y0*yMiddle>0) 
					x0=middle;
				else 
					x1=middle;

				middle=(x0+x1)/2;
				yMiddle = f(middle);
				y0=this.f(x0);
			}
		}

		//The middle point represents the x closest to 
		//the x-axis of the polynom
		return middle;
	}

	//Substruct p from this Polynom
	public void substruct(Polynom p) {

		boolean differ = true;
		int i, j;
		for(i=0 ; i < p.arr.size() ; i++) {
			for(j=0 ; j < this.arr.size() ; j++) {
				//If the power of monom in arr equal to monom in polynom p
				if(p.arr.get(i).getN() == this.arr.get(j).getN()) {
					this.arr.get(j).setA(this.arr.get(j).getA() - p.arr.get(i).getA()); 

					//If a monom array exists that is zero
					for(int k = 0 ; k < this.arr.size() ; k++) {
						if(this.arr.get(k).isZero()) this.arr.remove(k);
					}

					differ = false;
				}
			}
			//Check if the momon in Polynom p is diffent from all monoms in arr
			//To add his subtraction to the polynom
			if(differ) {
				Monom minus = new Monom(-p.arr.get(i).getA(),p.arr.get(i).getN());
				add(minus);
				differ = true;
			}
		}
	}

	//***My Addition for calculate the value of f(x);
	public double f(double x) {
		double fX = 0;
		for(int i=0 ; i < arr.size() ; i++) {
			fX = fX + this.arr.get(i).f(x);
		}
		return fX;
	}

	//**My addition for checking the output of the polynom
	public String toString() {
		String poly = "";

		for(int i = 0 ; i < this.arr.size() ; i++) {
			poly += "("+this.arr.get(i)+")+";
		}

		return poly;
	}
}
