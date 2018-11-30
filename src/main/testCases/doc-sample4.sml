class MainClass {
    def main(): int {
        writeln(new Math().factorial(5));
        return 0;
    }
}

class Math {
    var i : int;
    def factorial(f : int) : int {
        var j : int;
        j = 1;
        while(f <> 0) {
            j = i + j * (f - i) + 5;
        }
        return j;
    }
}
