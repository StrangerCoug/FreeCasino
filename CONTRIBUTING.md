# Contributing guidelines
Before contributing, please read the following:
* If you have a bug or a feature request, first search the issues tab to make sure somebody hasn't already opened an issue describing your bug or feature request. If it's not there, then open up an issue and fill out the template as appropriate to your issue. Please do not open an issue simply to ask a question; instead, go to the [Discussions](https://github.com/StrangerCoug/FreeCasino/discussions) tab.
* If your feature request is for a new game, we generally need a clear description of how the game is played from a third party. (A good source that I recommend is [The Wizard of Odds](https://wizardofodds.com/).) This is to ensure that the game is sufficiently well-known to warrant inclusion in the game. We allow exceptions to this policy for _original_ slots games only. As an open-source project, we cannot accept proprietary games or rules—this is why, for example, there are no plans for Spanish 21 (as it is owned by Masque Publishing).

## Style conventions
In general, code should closely follow Oracle's style guide for both [Java](https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html) and [JavaDoc](https://www.oracle.com/technetwork/java/javase/documentation/index-137868.html). They were written to ensure consistency, legibility, and maintainability. Deviating from the letter of Oracle's guides is fine, but the general principles behind them apply to this project.

We slightly differ from Oracle's indentation convention in that we follow the "tabs for indentation, spaces for alignment" principle in this project. The tab character counts _once_ toward the 80-character limit per line just like everything else. For continuation indents, use two hard tabs.

### Testing
Any class that overrides `equals()` and `hashCode()` (even implicitly), as well as all records, must have a test case where the overridden `equals()` and `hashCode()` are tested with `EqualsVerifier`. _If and only if_ `equals()` and `hashCode()` are inherited directly from `Object`, then you do not need to use `EqualsVerifier`. See [Java equals() and hashCode() Contracts](https://www.baeldung.com/java-equals-hashcode-contracts) by Baeldung to learn more about them and why they're important, and see [Error messages explained](https://jqno.nl/equalsverifier/errormessages/) by the official EqualsVerifier page if you need help understanding why a case being tested with the EqualsVerifier class is failing.

## Versioning scheme
We use [Semantic Versioning 2.0.0](https://semver.org/) when assigning release numbers in this project.
