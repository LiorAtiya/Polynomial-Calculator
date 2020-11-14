package M_4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomTest {

    ///////////// Monom Test /////////////
    @Test
    void testConstactorStringMonom() {
        Monom m1 = new Monom("0");
        Monom m2 = new Monom("2");
        Monom m3 = new Monom("-3.2x^2");
        Monom m4 = new Monom("-x");

        assertEquals("0.0 * x^0", m1.toString());
        assertEquals("2.0 * x^0", m2.toString());
        assertEquals("-3.2 * x^2", m3.toString());
        assertEquals("-1.0 * x^1", m4.toString());
    }

    @Test
    void testIsZero() {
        Monom m1 = new Monom("0");
        Monom m2 = new Monom("-x");

        assertTrue(m1.isZero());
        assertFalse(m2.isZero());
    }

    @Test
    void testEquals() {
        Monom m1 = new Monom(2, 3);
        Monom m2 = new Monom(m1);

        assertTrue(m1.equals(m2));
    }

    @Test
    void testAdd() {
        Monom m1 = new Monom(2, 3);
        Monom m2 = new Monom(2.1, 3);

        m1.add(m2);
        String expected = "4.1 * x^3";
        assertEquals(expected, m1.toString());
    }

    @Test
    void testMultiply() {
        Monom m1 = new Monom(2, 3);
        Monom m2 = new Monom("-3x^1");

        m1.multiply(m2);
        String expected = "-6.0 * x^4";
        assertEquals(expected, m1.toString());
    }

    @Test
    void testF() {
        Monom m1 = new Monom(2, 2);
        double actual = m1.f(2);
        double expected = 8.0;

        assertEquals(actual, expected, 0);
    }

    @Test
    void testCompareTo() {
        Monom m1 = new Monom(2, 2);
        Monom m2 = new Monom(1, 1);
        Monom m3 = new Monom(3, 3);

        assertEquals(1, m1.compareTo(m2));
        assertEquals(-1, m1.compareTo(m3));
        assertEquals(0, m1.compareTo(m1));
    }

    @Test
    void testDerivative() {
        Monom m1 = new Monom(2, 0);
        Monom m2 = new Monom(2, 2);
        Monom der = m1.derivative();
        Monom der2 = m2.derivative();

        assertEquals("0.0 * x^0", der.toString());
        assertEquals("4.0 * x^1", der2.toString());

    }


    /////// Polynom Test //////////
    @Test
    void testAddMonom() {
        Polynom pol1 = new Polynom();
        pol1.add(new Monom(2, 2));
        pol1.add(new Monom(1, 1));
        pol1.add(new Monom("5"));
        pol1.add(new Monom("0"));

        assertEquals("(2.0 * x^2)+(1.0 * x^1)+(5.0 * x^0)+", pol1.toString());
    }

    @Test
    void testAddPolynom() {
        Polynom pol1 = new Polynom();
        pol1.add(new Monom(2, 2));
        Polynom pol2 = new Polynom(pol1);

        pol1.add(pol2);
        assertEquals("(4.0 * x^2)+", pol1.toString());
    }

    @Test
    void testF2() {
        Polynom pol1 = new Polynom();
        pol1.add(new Monom(2, 2));
        pol1.add(new Monom(3, 3));

        double expected = 32;
        assertEquals(expected, pol1.f(2), 0);
    }

    @Test
    void testArea() {
        Polynom pol1 = new Polynom();
        pol1.add(new Monom("2"));
        pol1.add(new Monom("2x^2"));

        double expected = 2.6567;
        assertEquals(expected, pol1.area(0, 1, 0.01), 0);

    }

    @Test
    void testDerivative2() {
        Polynom pol1 = new Polynom();
        pol1.add(new Monom(2, 2));
        pol1.add(new Monom(3, 5));

        String expected = "(15.0 * x^4)+(4.0 * x^1)+";
        assertEquals(expected, pol1.derivative().toString());
    }

    @Test
    void testEquals2() {
        Polynom pol1 = new Polynom();
        pol1.add(new Monom(2, 2));
        pol1.add(new Monom(3, 5));
        Polynom pol2 = new Polynom(pol1);

        assertTrue(pol1.equals(pol2));
    }

    @Test
    void TestisZero2() {
        Polynom pol1 = new Polynom();
        pol1.add(new Monom("0"));

        assertTrue(pol1.isZero());
    }

    @Test
    void testMultiply2() {
        Polynom pol1 = new Polynom();
        pol1.add(new Monom(2, 2));
        pol1.add(new Monom(3, 5));

        Polynom pol2 = new Polynom();
        pol2.add(new Monom(2, 2));
        pol2.add(new Monom(4, 4));

        pol1.multiply(pol2);
        String expected = "(12.0 * x^9)+(6.0 * x^7)+(8.0 * x^6)+(4.0 * x^4)+";
        assertEquals(expected, pol1.toString());
    }

    @Test
    void testRoot() {
        Polynom pol1 = new Polynom();
        pol1.add(new Monom(2, 3));
        pol1.add(new Monom(3, 2));
        pol1.add(new Monom("4"));

        double expected = -2.0;
        assertEquals(expected, pol1.root(-5, 0, 0.01), 0);

    }

    @Test
    void testSubstruct() {
        Polynom pol1 = new Polynom();
        pol1.add(new Monom(2, 2));
        pol1.add(new Monom(3, 5));

        Polynom pol2 = new Polynom();
        pol2.add(new Monom(2, 2));
        pol2.add(new Monom(4, 4));

        pol1.substruct(pol2);
        String expected = "(3.0 * x^5)+(-4.0 * x^4)+";
        assertEquals(expected, pol1.toString());
    }
}