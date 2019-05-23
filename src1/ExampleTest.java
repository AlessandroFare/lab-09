package it.unimi.di.se.lab09;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(2); // 2 seconds max per test

	private static String input="Sopra la panca la capra campa\n" +
			"sotto la panca\n" +
			"la capra crepa";

	private static String output="Sopra    [0]\n" +
			"campa    [0]\n" +
			"capra    [0, 2]\n" +
			"crepa    [2]\n" +
			"la       [0, 1, 2]\n" +
			"panca    [0, 1]\n" +
			"sotto    [1]\n";

	@Test
	public void acceptance1() {
		InvertedIndex index=new InvertedIndex(new StringReader(input)).
				setFormatStrategy(new JustifiedFormatStrategy()).
				setOrderStrategy(new AlphabeticalOrderStrategy());
		index.build();
		assertThat(index.toString()).isEqualTo(output);
	}

	@Test
	public void acceptance2() {
		String out="la       [0, 1, 2]\n" +
				"capra    [0, 2]\n" +
				"panca    [0, 1]\n" +
				"Sopra    [0]\n" +
				"campa    [0]\n" +
				"crepa    [2]\n" +
				"sotto    [1]\n";
		InvertedIndex index=new InvertedIndex(new StringReader(input)).
				setFormatStrategy(new JustifiedFormatStrategy()).
				setOrderStrategy(new NumbersOrderStrategy());
		index.build();
		assertThat(index.toString()).isEqualTo(out);
	}

	@Test
	public void acceptance3() {
		String out="[0] --> la       [0, 1, 2]\n" +
				"[1] --> capra    [0, 2]\n" +
				"[2] --> panca    [0, 1]\n" +
				"[3] --> Sopra    [0]\n" +
				"[4] --> campa    [0]\n" +
				"[5] --> crepa    [2]\n" +
				"[6] --> sotto    [1]\n";
		InvertedIndex index=new InvertedIndex(new StringReader(input)).
				setFormatStrategy(new IndexFormatStrategy()).
				setOrderStrategy(new NumbersOrderStrategy());
		index.build();
		assertThat(index.toString()).isEqualTo(out);
	}
}
