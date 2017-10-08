void main() {
	 int x;
	println("TESTING COMPLEX ASSIGNMENTS");
  x = 5;
  println(x); // prints 5
  x += 1;
  println(x); // prints 6
  x -= 1;
  println(x); // prints 5
  x *= 2;
  println(x); // prints 10
  x /= 5;
  println(x); // prints 2
  x++;
  println(x); // prints 3
  x--;
  println(x); // prints 2

	println("TESTING UNARY OPERATORS");
	println(!true);
	println(!false);
	println(!!true);
	println(!!false);

	println(-(1 + 1));
	println(-(1 - 1));
	println(-(1 - 2));

	println("TESTING RELATIONAL OPERATORS");
	println(1 < 1);
	println(1 < 2);
	println(1 <= 2);
	println(1 <= 1);

	println(1 != 1);
	println(1 != 2);
	println(1 == 1);
	println(1 == 2);

	println(1 > 2);
	println(2 > 1);
	println(1 >= 2);
	println(1 >= 1);
	println(2 >= 1);

	println("TESTING AND / OR OPERATIONS");
	println(true && true);
	println(true && false);
	println(false && true);
	println(false && false);
	println(true || true);
	println(true || false);
	println(false || true);
	println(false || false);


}