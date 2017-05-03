package kr.doo.bind;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;

@SuppressWarnings("serial")
public class UpperSnakeCaseStrategy extends SnakeCaseStrategy {

	@Override
	public String translate(String input) {
		String upperCase = super.translate(input);
		return upperCase.toUpperCase();
	}
}
