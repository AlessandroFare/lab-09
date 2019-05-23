package it.unimi.di.se.lab09;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(2); // 2 seconds max per test

	@Test
	public void test1() {
		String input="Sopra la panca la capra campa\n" +
				"sotto la panca\n" +
				"la capra crepa";

		String output="0: Sopra la panca la capra campa\n" +
				"1: sotto la panca\n" +
				"2: la capra crepa";

		InvertedIndex index=new InvertedIndex(new StringReader(input));

		assertThat(index.toString()).isEqualTo(output);
	}

	@Test
	public void test6() {
		String input= "Sopra la panca\n"
					  +"la capra";

		String ouput= "Sopra [0]\nla [0, 1]\npanca [0]\ncapra [1]\n";

		InvertedIndex index=new InvertedIndex(new StringReader(input)).setFormatStrategy(new SimpleFormatStrategy());

		index.build();

		assertThat(index.output()).isEqualTo(ouput);
	}

	@Test
	public void test10() {
		String input= "Sopra la panca\n"
				+"la capra";

		String output= "Sopra     [0]\nla        [0, 1]\npanca     [0]\ncapra     [1]\n";

		InvertedIndex index=new InvertedIndex(new StringReader(input)).setFormatStrategy(new JustifiedFormatStrategy("%-10s"));

		index.build();

		assertThat(index.output()).isEqualTo(output);
	}

	@Test
	public void test3() {
		String input= "Sopra la panca\n"
				+"la capra";


		StopWordFilterStrategy filter=new StopWordFilterStrategy()
				.addStopWord("la")
				.addStopWord("panca");
		String output= "Sopra     [0]\ncapra     [1]\n";

		InvertedIndex index=new InvertedIndex(new StringReader(input)).
				setOrderStrategy(new NumbersOrderStrategy()).
				setFormatStrategy(new JustifiedFormatStrategy("%-10s"))
				.setFilterStrategy(filter);

		index.build();

		assertThat(index.output()).isEqualTo(output);
	}
	//MANCA IL FILTRO SU STOPWORLD E SEGNI DI PUNTEGGIATURA

}
