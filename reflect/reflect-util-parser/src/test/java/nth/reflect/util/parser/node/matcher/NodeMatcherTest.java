package nth.reflect.util.parser.node.matcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.Test;

import nth.reflect.util.parser.node.Node;
import nth.reflect.util.parser.node.matcher.result.MatchResults;
import nth.reflect.util.parser.node.matcher.rule.MatchRules;
import nth.reflect.util.parser.node.matcher.rule.Repetition;

class NodeMatcherTest {

	private static final String GIVEN_RULES_WHERE_NOT_FOUND = "Given rules where not found!";
	private static final String NO_RESULTS_WHERE_FOUND = "No results where found!";

	//# Basic 
	@Test
	void testMatch_givenABCDE_givenRulesA_foundOnPos0() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules().add(PredicateTestFactory.create("A"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(0);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(0);
	}

	@Test
	void testMatch_givenABCDE_givenRulesAB_foundOnPos0UntilPos1() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("A"))//
				.add(PredicateTestFactory.create("B"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(0);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(1);
	}

	@Test
	void testMatch_givenABCDE_givenRulesABCDE_foundOnPos0UntilPos4() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("A"))//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"))//
				.add(PredicateTestFactory.create("D"))//
				.add(PredicateTestFactory.create("E"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(0);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(4);
	}

	@Test
	void testMatch_givenABCDE_givenRulesB_foundOnPos1() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(1);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(1);

	}

	@Test
	void testMatch_givenABCDE_givenRulesBC_foundOnPos1UntilPos2() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(1);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(2);

	}

	@Test
	void testMatch_givenABCDE_givenRulesDE_foundOnPos3UntilPos4() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("D"))//
				.add(PredicateTestFactory.create("E"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(3);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(4);

	}

	@Test
	void testMatch_givenABCDE_givenRulesE_foundOnPos4() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("E"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(4);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(4);

	}
	
	//#basic not found
	@Test
	void testMatch_givenABCDE_givenRulesZ_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("Z"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);
	}

	@Test
	void testMatch_givenABCDE_givenRulesAZ_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("A"))//
				.add(PredicateTestFactory.create("Z"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);
	}

	@Test
	void testMatch_givenABCDE_givenRulesCB_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("C"))//
				.add(PredicateTestFactory.create("B"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);

	}

	@Test
	void testMatch_givenABCDE_givenRulesED_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("E"))//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);
	}
	
	//#sub rules

	@Test
	void testMatch_givenABCDE_givenRulesCD_givenRulesBCDE_foundRuleCDOnPos2UntilPos3() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules cdRules = new MatchRules()//
				.add(PredicateTestFactory.create("C"))//
				.add(PredicateTestFactory.create("D"));
		MatchRules bcdeRules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(cdRules)//
				.add(PredicateTestFactory.create("E"));
		
		NodeMatcher matcher = new NodeMatcher(bcdeRules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult(cdRules).getNodeIndex()).isEqualTo(2);
		assertThat(results.getLastResult(cdRules).getNodeIndex()).isEqualTo(3);
	}

	@Test
	void testMatch_givenABCDE_givenRulesCD_givenRulesBCDE__givenRulesZ_rulesZNotFoundException() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules cdRules = new MatchRules()//
				.add(PredicateTestFactory.create("C"))//
				.add(PredicateTestFactory.create("D"));
		MatchRules bcdeRules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(cdRules)//
				.add(PredicateTestFactory.create("E"));
		MatchRules zRules = new MatchRules()//
				.add(PredicateTestFactory.create("Z"));
		
		NodeMatcher matcher = new NodeMatcher(bcdeRules);
		MatchResults results = matcher.match(nodes);
		assertThatThrownBy(()->results.getFirstResult(zRules)).hasMessage(GIVEN_RULES_WHERE_NOT_FOUND);
	}

	//#repetition zeroOrOnce
	@Test
	void testMatch_givenABDE_givenRulesBCzeroOrOnceD_foundOnPos1UntilPos2() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.zeroOrOnce())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(1);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(2);
	}

	@Test
	void testMatch_givenABCDE_givenRulesBCzeroOrOnceD_foundOnPos1UntilPos3() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.zeroOrOnce())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(1);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(3);
	}

	@Test
	void testMatch_givenABCCDE_givenRulesBCzeroOrOnceD_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.zeroOrOnce())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);
	}

	//#repetition zeroOrMore
	@Test
	void testMatch_givenABDE_givenRulesBCzeroOrMoreD_foundOnPos1UntilPos2() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.zeroOrMore())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(1);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(2);
	}

	@Test
	void testMatch_givenABCDE_givenRulesBCzeroOrMoreD_foundOnPos1UntilPos3() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.zeroOrMore())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(1);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(3);
	}

	@Test
	void testMatch_givenABCCDE_givenRulesBCzeroOrMoreD_foundOnPos1UntilPos4() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.zeroOrMore())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(1);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(4);
	}


	//#repetition once
	@Test
	void testMatch_givenABDE_givenRulesBConceD_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.once())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);
	}

	@Test
	void testMatch_givenABCDE_givenRulesBConceD_foundOnPos1UntilPos3() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.once())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(1);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(3);
	}

	@Test
	void testMatch_givenABCCDE_givenRulesBConceD_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.once())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);
	}

	//#repetition once
	@Test
	void testMatch_givenABDE_givenRulesBConceOrMoreD_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.onceOrMore())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);
	}

	@Test
	void testMatch_givenABCDE_givenRulesBConceOrMoreD_foundOnPos1UntilPos3() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.onceOrMore())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(1);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(3);
	}

	@Test
	void testMatch_givenABCCDE_givenRulesBConceOrMoreD_foundOnPos1UntilPos4() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"),Repetition.onceOrMore())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(1);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(4);
	}
	
	//#Reluctant
	
	@Test
	void testMatch_givenABCDE_givenRulesBAnyD_foundOnPos1UntilPos4() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.createAny(),Repetition.onceOrMore())//
				.add(PredicateTestFactory.create("D"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(1);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(3);
	}
	
	
	@Test
	void testMatch_givenABCDECA_givenRulesAnyZeroOrMoreReluctantC_foundOnPos0UntilPos2() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDECA");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.createAny(),Repetition.zeroOrMore().reluctant())
				.add(PredicateTestFactory.create("C"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(0);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(2);
	}

	//#Eager


	@Test
	void testMatch_givenABCDECA_givenRulesDAnyZeroOrMoreEager_foundOnPos3UntilPos6() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDECA");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("D"))//
				.add(PredicateTestFactory.createAny(),Repetition.onceOrMore());//eager by default
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(3);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(6);
	}


	@Test
	void testMatch_givenABCDECA_givenRulesAnyZeroOrMoreEagerC_foundOnPos0UntilPos5() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDECA");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.createAny(),Repetition.onceOrMore())//eager by default
				.add(PredicateTestFactory.create("C"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(0);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(5);
	}

	@Test
	void testMatch_givenABCDECA_givenRulesAnyZeroOrMoreEagerCB_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDECA");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.createAny(),Repetition.onceOrMore())//eager by default
				.add(PredicateTestFactory.create("C"))//
				.add(PredicateTestFactory.create("B"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);
	}

	//#  first Match Must Be First Node
	
	@Test
	void testMatch_givenABCDE_givenRulesABfirstMatchMustBeFirstNode_foundOnPos0UntilPos1() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.firstMatchMustBeFirstNode()
				.add(PredicateTestFactory.create("A"))//
				.add(PredicateTestFactory.create("B"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(0);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(1);
	}

	@Test
	void testMatch_givenABCDE_givenRulesBCfirstMatchMustBeFirstNode_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.firstMatchMustBeFirstNode()
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"));
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);
	}
	
	//# last Match Must Be Last Node
	@Test
	void testMatch_givenABCDE_givenRulesDElastMatchMustBeLastNode_foundOnPos3UntilPos4() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("D"))//
				.add(PredicateTestFactory.create("E"))//
				.lastMatchMustBeLastNode();
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(3);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(4);
	}
	
	@Test
	void testMatch_givenABCDE_givenRulesCDlastMatchMustBeLastNode_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.add(PredicateTestFactory.create("C"))//
				.add(PredicateTestFactory.create("D"))//
				.lastMatchMustBeLastNode();
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);
	}

	//#  first Match Must Be First Node and last Match Must Be Last Node
	@Test
	void testMatch_givenABCDE_givenRulesABCDEfirstMatchCanBeAnyNodeLastMatchMustBeLastNode_foundOnPos0UntilPos4() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.firstMatchCanBeAnyNode()//
				.add(PredicateTestFactory.create("A"))//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"))//
				.add(PredicateTestFactory.create("D"))//
				.add(PredicateTestFactory.create("E"))//
				.lastMatchMustBeLastNode();
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.getFirstResult().getNodeIndex()).isEqualTo(0);
		assertThat(results.getLastResult().getNodeIndex()).isEqualTo(4);
	}

	@Test
	void testMatch_givenABCDE_givenRulesBCDfirstMatchCanBeAnyNodeLastMatchMustBeLastNode_notFound() {
		List<Node> nodes = NodeTestFactory.createTokenNodes("ABCDE");
		MatchRules rules = new MatchRules()//
				.firstMatchCanBeAnyNode()//
				.add(PredicateTestFactory.create("B"))//
				.add(PredicateTestFactory.create("C"))//
				.add(PredicateTestFactory.create("D"))//
				.lastMatchMustBeLastNode();
		NodeMatcher matcher = new NodeMatcher(rules);
		MatchResults results = matcher.match(nodes);
		assertThat(results.hasResults()).isFalse();
		assertThatThrownBy(() -> results.getFirstResult().getNodeIndex()).hasMessageContaining(NO_RESULTS_WHERE_FOUND);
	}

}