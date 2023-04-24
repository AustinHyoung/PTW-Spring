package com.api.ptw.board.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.ptw.board.service.BoardService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/apis")
public class BoardController {
	
	@Resource
	private BoardService boardService;
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/boardlist")
	public Object getBoardList(HttpSession session) {
		Map<String, Object> userInfo = new HashMap<String, Object>();
		
		try {
			userInfo = (Map<String,Object>)session.getAttribute("session");
			List<Map<String, Object>> boardList = boardService.getBoardList(userInfo);
			System.out.println(boardList);
			return boardList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/create/board")
	public Object createBoard(HttpSession session,
							@RequestParam Map<String, Object> paramMap) {
		
		Map<String, Object> userInfo = new HashMap<String, Object>();
		Map<String, Object> resObj = new HashMap<String, Object>();
		try {
			
			userInfo = (Map<String,Object>)session.getAttribute("session");
			
			Map<String, Object> boardInput = new HashMap<>();
			boardInput.put("member_no", userInfo.get("member_no"));
			boardInput.put("title", paramMap.get("title"));
			
			boardService.createBoard(boardInput);
			
			// 최근 보드 넘버 **************cards_list 초기데이터
			Map<String, Object> lastBoardNo = boardService.getLastBoard(boardInput);
			System.out.println(lastBoardNo);
			
			List<String> initialTitle = new ArrayList<>();
			
			initialTitle.add("Do");
			initialTitle.add("Done");
			initialTitle.add("Hold");
			
			List<Map<String, Object>> initialData = new ArrayList<>();
			int initialCardListPosition = 0;
			
			for (String title: initialTitle) {
				Map<String, Object> data = new HashMap<>();
				
				data.put("board_no", lastBoardNo.get("last_board_no"));
				data.put("title", title);
				data.put("list_order", initialCardListPosition);
				initialData.add(data);
				
				initialCardListPosition += 1;
			}
			
			System.out.println(initialData);
			
			boardService.initialCardsList(initialData);
			
			// **********card 초기데이터
			// **********GET cardListNo
			Map<String, Object> cardParamMap = new HashMap<String, Object>();
			cardParamMap.put("board_no", lastBoardNo.get("last_board_no"));
			cardParamMap.put("title", "Do");
			
			Map<String, Object> cardListNo = boardService.getCardListNo(cardParamMap);
			
			// **********Insert card
			
			List<String> initialContents = new ArrayList<>();
			
			initialContents.add("Do 1");
			initialContents.add("Do 2");
			initialContents.add("Do 3");
			
			List<Map<String, Object>> initialCardData = new ArrayList<>();
			int initialCardPosition = 0;
			
			for (String content: initialContents) {
				Map<String, Object> data = new HashMap<>();
				
				data.put("contents", content);
				data.put("card_list_no", cardListNo.get("cards_list_no"));
				data.put("board_no", lastBoardNo.get("last_board_no"));
				data.put("card_order", initialCardPosition);
				initialCardData.add(data);
				
				initialCardPosition += 1;
			}
			
			boardService.initialCard(initialCardData);
			
			resObj.put("code", HttpStatus.OK.value());
			
			return resObj;
		} catch (Exception e) {
			e.printStackTrace();
			
			resObj.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			
			return resObj;
		}
	
	}
	
	
	@PutMapping(path = "/card/change")
	public @ResponseBody Object doCardChange(@RequestBody Map<String, Object> paramMap) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			boardService.updateCard(paramMap);
			
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@GetMapping(path = "/board/initial")
	public Object getBoardInitial(HttpSession session,
							@RequestParam Map<String, Object> paramMap) {
		
		Map<String, Object> userInfo = new HashMap<String, Object>();
		
		try {
			System.out.println(paramMap);
			// board_no, title
			Map<String, Object> resObj = new HashMap<String, Object>();
			resObj.put("board_no", paramMap.get("board_no"));
			resObj.put("title", paramMap.get("title"));
			
			// db에서 가져온 데이터 cards_list
			List<Map<String, Object>> cards_list = boardService.getCardList(paramMap);
			
			// db에서 가져온 데이터 card
			List<Map<String, Object>> card = boardService.getCard(paramMap);
			
			// resObj에 cards_list 로 가져올 리스트
			List<Map<String, Object>> cardsList = new ArrayList<Map<String, Object>>();
			
			// 가져온 cards_list 데이터를 반복시킴
			for(Map<String, Object> cardListMap: cards_list) {
				// map을 만들어서 하나씩 데이터를 새로운 key로 db에서 가져온 데이터를 삽입
				Map<String, Object> cardsListMap = new HashMap<String, Object>();
				cardsListMap.put("cards_list_no", cardListMap.get("cards_list_no"));
				cardsListMap.put("title", cardListMap.get("title"));
				cardsListMap.put("list_order", cardListMap.get("list_order"));
				// 새로운 card의 리스트 생성 
				List<Map<String, Object>> cardsListCardList = new ArrayList<Map<String, Object>>();
				// 가져온 card 데이터를 반복시킴
				for (Map<String, Object> cardMap: card) {
					// card와 card_list의 no이 같은것만 map에 삽입
					if(cardMap.get("card_list_no").equals(cardListMap.get("cards_list_no"))) {
						Map<String, Object> cardsListCardMap = new HashMap<String, Object>();
						cardsListCardMap.put("card_no", cardMap.get("card_no"));
						cardsListCardMap.put("contents", cardMap.get("contents"));
						cardsListCardMap.put("card_order", cardMap.get("card_order"));
						
						cardsListCardList.add(cardsListCardMap);
					}
				}
				
				Collections.sort(cardsListCardList, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> card1, Map<String, Object> card2) {
						int cardOrder1 = (int) card1.get("card_order");
						int cardOrder2 = (int) card2.get("card_order");
						return Integer.compare(cardOrder1, cardOrder2);
					}
				});
				
				System.out.println("to server list::::" + cardsListCardList);
				
				cardsListMap.put("card", cardsListCardList);
				cardsList.add(cardsListMap);
			}
			
			
			resObj.put("cards_list", cardsList);
			
			System.out.println(resObj);
			
			
			return resObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	
	@PutMapping(path = "/set/position")
	public @ResponseBody Object doUpdatePosition(@RequestBody Map<String, Object> paramMap) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			System.out.println(paramMap);
			
			
			int destination_order = (int)paramMap.get("destination_list_order");
			int source_order = (int)paramMap.get("source_list_order");
			
			if (source_order < destination_order) {
				boardService.cardsListHighPosition(paramMap);
			}
			else {
				boardService.cardsListLowPosition(paramMap);
			}
			
			boardService.cardsListInsertPosition(paramMap);
			
			resObj.put("board_no", paramMap.get("board_no"));
			resObj.put("title", paramMap.get("title"));
			
			// db에서 가져온 데이터 cards_list
			List<Map<String, Object>> cards_list = boardService.getCardList(paramMap);
			
			// db에서 가져온 데이터 card
			List<Map<String, Object>> card = boardService.getCard(paramMap);
			
			// resObj에 cards_list 로 가져올 리스트
			List<Map<String, Object>> cardsList = new ArrayList<Map<String, Object>>();
			
			// 가져온 cards_list 데이터를 반복시킴
			for(Map<String, Object> cardListMap: cards_list) {
				// map을 만들어서 하나씩 데이터를 새로운 key로 db에서 가져온 데이터를 삽입
				Map<String, Object> cardsListMap = new HashMap<String, Object>();
				cardsListMap.put("cards_list_no", cardListMap.get("cards_list_no"));
				cardsListMap.put("title", cardListMap.get("title"));
				cardsListMap.put("list_order", cardListMap.get("list_order"));
				// 새로운 card의 리스트 생성 
				List<Map<String, Object>> cardsListCardList = new ArrayList<Map<String, Object>>();
				// 가져온 card 데이터를 반복시킴
				for (Map<String, Object> cardMap: card) {
					// card와 card_list의 no이 같은것만 map에 삽입
					if(cardMap.get("card_list_no").equals(cardListMap.get("cards_list_no"))) {
						Map<String, Object> cardsListCardMap = new HashMap<String, Object>();
						cardsListCardMap.put("card_no", cardMap.get("card_no"));
						cardsListCardMap.put("contents", cardMap.get("contents"));
						cardsListCardMap.put("card_order", cardMap.get("card_order"));
						cardsListCardList.add(cardsListCardMap);
					}
				}
				
				Collections.sort(cardsListCardList, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> card1, Map<String, Object> card2) {
						int cardOrder1 = (int) card1.get("card_order");
						int cardOrder2 = (int) card2.get("card_order");
						return Integer.compare(cardOrder1, cardOrder2);
					}
				});
				
				System.out.println("to server list::::" + cardsListCardList);
				
				cardsListMap.put("card", cardsListCardList);
				cardsList.add(cardsListMap);
			}
			
			
			resObj.put("cards_list", cardsList);
			
			resObj.put("msg", "업데이트 완료");
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping(path = "/set/card/position")
	public @ResponseBody Object doUpdateCardPosition(@RequestBody Map<String, Object> paramMap) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			System.out.println(paramMap);
			
			boardService.cardPushPosition(paramMap);
			boardService.cardInsertPosition(paramMap);
			
			boardService.cardMissingPosition(paramMap);
			
			
			// board_no, title
			
			resObj.put("board_no", paramMap.get("board_no"));
			resObj.put("title", paramMap.get("title"));
			
			// db에서 가져온 데이터 cards_list
			List<Map<String, Object>> cards_list = boardService.getCardList(paramMap);
			
			// db에서 가져온 데이터 card
			List<Map<String, Object>> card = boardService.getCard(paramMap);
			
			// resObj에 cards_list 로 가져올 리스트
			List<Map<String, Object>> cardsList = new ArrayList<Map<String, Object>>();
			
			// 가져온 cards_list 데이터를 반복시킴
			for(Map<String, Object> cardListMap: cards_list) {
				// map을 만들어서 하나씩 데이터를 새로운 key로 db에서 가져온 데이터를 삽입
				Map<String, Object> cardsListMap = new HashMap<String, Object>();
				cardsListMap.put("cards_list_no", cardListMap.get("cards_list_no"));
				cardsListMap.put("title", cardListMap.get("title"));
				cardsListMap.put("list_order", cardListMap.get("list_order"));
				// 새로운 card의 리스트 생성 
				List<Map<String, Object>> cardsListCardList = new ArrayList<Map<String, Object>>();
				// 가져온 card 데이터를 반복시킴
				for (Map<String, Object> cardMap: card) {
					// card와 card_list의 no이 같은것만 map에 삽입
					if(cardMap.get("card_list_no").equals(cardListMap.get("cards_list_no"))) {
						Map<String, Object> cardsListCardMap = new HashMap<String, Object>();
						cardsListCardMap.put("card_no", cardMap.get("card_no"));
						cardsListCardMap.put("contents", cardMap.get("contents"));
						cardsListCardMap.put("card_order", cardMap.get("card_order"));
						
						cardsListCardList.add(cardsListCardMap);
					}
				}
				
				Collections.sort(cardsListCardList, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> card1, Map<String, Object> card2) {
						int cardOrder1 = (int) card1.get("card_order");
						int cardOrder2 = (int) card2.get("card_order");
						return Integer.compare(cardOrder1, cardOrder2);
					}
				});
				
				System.out.println("to server list::::" + cardsListCardList);
				
				cardsListMap.put("card", cardsListCardList);
				cardsList.add(cardsListMap);
			}
			
			
			resObj.put("cards_list", cardsList);
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping(path = "/set/card/same/position")
	public @ResponseBody Object doUpdateCardSamePosition(@RequestBody Map<String, Object> paramMap) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			int destination_order = (int)paramMap.get("destination_card_order");
			int source_order = (int)paramMap.get("source_card_order");
			
			if (source_order < destination_order) {
				boardService.cardHighSamePosition(paramMap);
			}
			else {
				boardService.cardLowSamePosition(paramMap);
			}
			
			boardService.cardSamePosition(paramMap);
			
			// board_no, title
			
			resObj.put("board_no", paramMap.get("board_no"));
			resObj.put("title", paramMap.get("title"));
			
			// db에서 가져온 데이터 cards_list
			List<Map<String, Object>> cards_list = boardService.getCardList(paramMap);
			
			// db에서 가져온 데이터 card
			List<Map<String, Object>> card = boardService.getCard(paramMap);
			
			// resObj에 cards_list 로 가져올 리스트
			List<Map<String, Object>> cardsList = new ArrayList<Map<String, Object>>();
			
			// 가져온 cards_list 데이터를 반복시킴
			for(Map<String, Object> cardListMap: cards_list) {
				// map을 만들어서 하나씩 데이터를 새로운 key로 db에서 가져온 데이터를 삽입
				Map<String, Object> cardsListMap = new HashMap<String, Object>();
				cardsListMap.put("cards_list_no", cardListMap.get("cards_list_no"));
				cardsListMap.put("title", cardListMap.get("title"));
				cardsListMap.put("list_order", cardListMap.get("list_order"));
				// 새로운 card의 리스트 생성 
				List<Map<String, Object>> cardsListCardList = new ArrayList<Map<String, Object>>();
				// 가져온 card 데이터를 반복시킴
				for (Map<String, Object> cardMap: card) {
					// card와 card_list의 no이 같은것만 map에 삽입
					if(cardMap.get("card_list_no").equals(cardListMap.get("cards_list_no"))) {
						Map<String, Object> cardsListCardMap = new HashMap<String, Object>();
						cardsListCardMap.put("card_no", cardMap.get("card_no"));
						cardsListCardMap.put("contents", cardMap.get("contents"));
						cardsListCardMap.put("card_order", cardMap.get("card_order"));
						
						cardsListCardList.add(cardsListCardMap);
					}
				}
				
				Collections.sort(cardsListCardList, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> card1, Map<String, Object> card2) {
						int cardOrder1 = (int) card1.get("card_order");
						int cardOrder2 = (int) card2.get("card_order");
						return Integer.compare(cardOrder1, cardOrder2);
					}
				});
				
				System.out.println("to server list::::" + cardsListCardList);
				
				cardsListMap.put("card", cardsListCardList);
				cardsList.add(cardsListMap);
			}
			
			
			resObj.put("cards_list", cardsList);
			
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping(path = "/delete/board/{board_no}")
	public Object deleteBoard (@PathVariable("board_no") int board_no) {
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			boardService.deleteBoard(board_no);
			
			resObj.put("code", HttpStatus.OK.value());
			
			return resObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping(path = "/add/cardslist")
	public @ResponseBody Object addCardsList (@RequestBody Map<String, Object> paramMap) {
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			boardService.addCardsList(paramMap);
			
			// board_no, title
	
			resObj.put("board_no", paramMap.get("board_no"));
			resObj.put("title", paramMap.get("board_title"));
			
			// db에서 가져온 데이터 cards_list
			List<Map<String, Object>> cards_list = boardService.getCardList(paramMap);
			
			// db에서 가져온 데이터 card
			List<Map<String, Object>> card = boardService.getCard(paramMap);
			
			// resObj에 cards_list 로 가져올 리스트
			List<Map<String, Object>> cardsList = new ArrayList<Map<String, Object>>();
			
			// 가져온 cards_list 데이터를 반복시킴
			for(Map<String, Object> cardListMap: cards_list) {
				// map을 만들어서 하나씩 데이터를 새로운 key로 db에서 가져온 데이터를 삽입
				Map<String, Object> cardsListMap = new HashMap<String, Object>();
				cardsListMap.put("cards_list_no", cardListMap.get("cards_list_no"));
				cardsListMap.put("title", cardListMap.get("title"));
				cardsListMap.put("list_order", cardListMap.get("list_order"));
				// 새로운 card의 리스트 생성 
				List<Map<String, Object>> cardsListCardList = new ArrayList<Map<String, Object>>();
				// 가져온 card 데이터를 반복시킴
				for (Map<String, Object> cardMap: card) {
					// card와 card_list의 no이 같은것만 map에 삽입
					if(cardMap.get("card_list_no").equals(cardListMap.get("cards_list_no"))) {
						Map<String, Object> cardsListCardMap = new HashMap<String, Object>();
						cardsListCardMap.put("card_no", cardMap.get("card_no"));
						cardsListCardMap.put("contents", cardMap.get("contents"));
						cardsListCardMap.put("card_order", cardMap.get("card_order"));
						
						cardsListCardList.add(cardsListCardMap);
					}
				}
				
				Collections.sort(cardsListCardList, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> card1, Map<String, Object> card2) {
						int cardOrder1 = (int) card1.get("card_order");
						int cardOrder2 = (int) card2.get("card_order");
						return Integer.compare(cardOrder1, cardOrder2);
					}
				});
				
				System.out.println("to server list::::" + cardsListCardList);
				
				cardsListMap.put("card", cardsListCardList);
				cardsList.add(cardsListMap);
			}
			
			
			resObj.put("cards_list", cardsList);
			
			
			return resObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping(path = "/edit/cardslist")
	public @ResponseBody Object editCardsList(@RequestBody Map<String, Object> paramMap) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			boardService.editCardsList(paramMap);
			
			// board_no, title
			resObj.put("board_no", paramMap.get("board_no"));
			resObj.put("title", paramMap.get("board_title"));
			
			// db에서 가져온 데이터 cards_list
			List<Map<String, Object>> cards_list = boardService.getCardList(paramMap);
			
			// db에서 가져온 데이터 card
			List<Map<String, Object>> card = boardService.getCard(paramMap);
			
			// resObj에 cards_list 로 가져올 리스트
			List<Map<String, Object>> cardsList = new ArrayList<Map<String, Object>>();
			
			// 가져온 cards_list 데이터를 반복시킴
			for(Map<String, Object> cardListMap: cards_list) {
				// map을 만들어서 하나씩 데이터를 새로운 key로 db에서 가져온 데이터를 삽입
				Map<String, Object> cardsListMap = new HashMap<String, Object>();
				cardsListMap.put("cards_list_no", cardListMap.get("cards_list_no"));
				cardsListMap.put("title", cardListMap.get("title"));
				cardsListMap.put("list_order", cardListMap.get("list_order"));
				// 새로운 card의 리스트 생성 
				List<Map<String, Object>> cardsListCardList = new ArrayList<Map<String, Object>>();
				// 가져온 card 데이터를 반복시킴
				for (Map<String, Object> cardMap: card) {
					// card와 card_list의 no이 같은것만 map에 삽입
					if(cardMap.get("card_list_no").equals(cardListMap.get("cards_list_no"))) {
						Map<String, Object> cardsListCardMap = new HashMap<String, Object>();
						cardsListCardMap.put("card_no", cardMap.get("card_no"));
						cardsListCardMap.put("contents", cardMap.get("contents"));
						cardsListCardMap.put("card_order", cardMap.get("card_order"));
						
						cardsListCardList.add(cardsListCardMap);
					}
				}
				
				Collections.sort(cardsListCardList, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> card1, Map<String, Object> card2) {
						int cardOrder1 = (int) card1.get("card_order");
						int cardOrder2 = (int) card2.get("card_order");
						return Integer.compare(cardOrder1, cardOrder2);
					}
				});
				
				System.out.println("to server list::::" + cardsListCardList);
				
				cardsListMap.put("card", cardsListCardList);
				cardsList.add(cardsListMap);
			}
			
			
			resObj.put("cards_list", cardsList);
			
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping(path = "/delete/cardslist")
	public @ResponseBody Object deleteCardsList (@RequestBody Map<String, Object> paramMap) {
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			System.out.println(paramMap);
			
			boardService.deleteCardsListPosition(paramMap);
			
			boardService.deleteCardsList(paramMap);
			
			// board_no, title
			resObj.put("board_no", paramMap.get("board_no"));
			resObj.put("title", paramMap.get("board_title"));
			
			// db에서 가져온 데이터 cards_list
			List<Map<String, Object>> cards_list = boardService.getCardList(paramMap);
			
			// db에서 가져온 데이터 card
			List<Map<String, Object>> card = boardService.getCard(paramMap);
			
			// resObj에 cards_list 로 가져올 리스트
			List<Map<String, Object>> cardsList = new ArrayList<Map<String, Object>>();
			
			// 가져온 cards_list 데이터를 반복시킴
			for(Map<String, Object> cardListMap: cards_list) {
				// map을 만들어서 하나씩 데이터를 새로운 key로 db에서 가져온 데이터를 삽입
				Map<String, Object> cardsListMap = new HashMap<String, Object>();
				cardsListMap.put("cards_list_no", cardListMap.get("cards_list_no"));
				cardsListMap.put("title", cardListMap.get("title"));
				cardsListMap.put("list_order", cardListMap.get("list_order"));
				// 새로운 card의 리스트 생성 
				List<Map<String, Object>> cardsListCardList = new ArrayList<Map<String, Object>>();
				// 가져온 card 데이터를 반복시킴
				for (Map<String, Object> cardMap: card) {
					// card와 card_list의 no이 같은것만 map에 삽입
					if(cardMap.get("card_list_no").equals(cardListMap.get("cards_list_no"))) {
						Map<String, Object> cardsListCardMap = new HashMap<String, Object>();
						cardsListCardMap.put("card_no", cardMap.get("card_no"));
						cardsListCardMap.put("contents", cardMap.get("contents"));
						cardsListCardMap.put("card_order", cardMap.get("card_order"));
						
						cardsListCardList.add(cardsListCardMap);
					}
				}
				
				Collections.sort(cardsListCardList, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> card1, Map<String, Object> card2) {
						int cardOrder1 = (int) card1.get("card_order");
						int cardOrder2 = (int) card2.get("card_order");
						return Integer.compare(cardOrder1, cardOrder2);
					}
				});
				
				System.out.println("to server list::::" + cardsListCardList);
				
				cardsListMap.put("card", cardsListCardList);
				cardsList.add(cardsListMap);
			}
			
			
			resObj.put("cards_list", cardsList);
			return resObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@PostMapping(path = "/add/card")
	public @ResponseBody Object addCard (@RequestBody Map<String, Object> paramMap) {
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			System.out.println(paramMap);
			
			boardService.addCard(paramMap);
			
			// board_no, title
	
			resObj.put("board_no", paramMap.get("board_no"));
			resObj.put("title", paramMap.get("board_title"));
			
			// db에서 가져온 데이터 cards_list
			List<Map<String, Object>> cards_list = boardService.getCardList(paramMap);
			
			// db에서 가져온 데이터 card
			List<Map<String, Object>> card = boardService.getCard(paramMap);
			
			// resObj에 cards_list 로 가져올 리스트
			List<Map<String, Object>> cardsList = new ArrayList<Map<String, Object>>();
			
			// 가져온 cards_list 데이터를 반복시킴
			for(Map<String, Object> cardListMap: cards_list) {
				// map을 만들어서 하나씩 데이터를 새로운 key로 db에서 가져온 데이터를 삽입
				Map<String, Object> cardsListMap = new HashMap<String, Object>();
				cardsListMap.put("cards_list_no", cardListMap.get("cards_list_no"));
				cardsListMap.put("title", cardListMap.get("title"));
				cardsListMap.put("list_order", cardListMap.get("list_order"));
				// 새로운 card의 리스트 생성 
				List<Map<String, Object>> cardsListCardList = new ArrayList<Map<String, Object>>();
				// 가져온 card 데이터를 반복시킴
				for (Map<String, Object> cardMap: card) {
					// card와 card_list의 no이 같은것만 map에 삽입
					if(cardMap.get("card_list_no").equals(cardListMap.get("cards_list_no"))) {
						Map<String, Object> cardsListCardMap = new HashMap<String, Object>();
						cardsListCardMap.put("card_no", cardMap.get("card_no"));
						cardsListCardMap.put("contents", cardMap.get("contents"));
						cardsListCardMap.put("card_order", cardMap.get("card_order"));
						
						cardsListCardList.add(cardsListCardMap);
					}
				}
				
				Collections.sort(cardsListCardList, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> card1, Map<String, Object> card2) {
						int cardOrder1 = (int) card1.get("card_order");
						int cardOrder2 = (int) card2.get("card_order");
						return Integer.compare(cardOrder1, cardOrder2);
					}
				});
				
				System.out.println("to server list::::" + cardsListCardList);
				
				cardsListMap.put("card", cardsListCardList);
				cardsList.add(cardsListMap);
			}
			
			
			resObj.put("cards_list", cardsList);
			
			
			return resObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping(path = "/edit/card")
	public @ResponseBody Object editCard(@RequestBody Map<String, Object> paramMap) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			boardService.editCard(paramMap);
			
			// board_no, title
			resObj.put("board_no", paramMap.get("board_no"));
			resObj.put("title", paramMap.get("title"));
			
			// db에서 가져온 데이터 cards_list
			List<Map<String, Object>> cards_list = boardService.getCardList(paramMap);
			
			// db에서 가져온 데이터 card
			List<Map<String, Object>> card = boardService.getCard(paramMap);
			
			// resObj에 cards_list 로 가져올 리스트
			List<Map<String, Object>> cardsList = new ArrayList<Map<String, Object>>();
			
			// 가져온 cards_list 데이터를 반복시킴
			for(Map<String, Object> cardListMap: cards_list) {
				// map을 만들어서 하나씩 데이터를 새로운 key로 db에서 가져온 데이터를 삽입
				Map<String, Object> cardsListMap = new HashMap<String, Object>();
				cardsListMap.put("cards_list_no", cardListMap.get("cards_list_no"));
				cardsListMap.put("title", cardListMap.get("title"));
				cardsListMap.put("list_order", cardListMap.get("list_order"));
				// 새로운 card의 리스트 생성 
				List<Map<String, Object>> cardsListCardList = new ArrayList<Map<String, Object>>();
				// 가져온 card 데이터를 반복시킴
				for (Map<String, Object> cardMap: card) {
					// card와 card_list의 no이 같은것만 map에 삽입
					if(cardMap.get("card_list_no").equals(cardListMap.get("cards_list_no"))) {
						Map<String, Object> cardsListCardMap = new HashMap<String, Object>();
						cardsListCardMap.put("card_no", cardMap.get("card_no"));
						cardsListCardMap.put("contents", cardMap.get("contents"));
						cardsListCardMap.put("card_order", cardMap.get("card_order"));
						
						cardsListCardList.add(cardsListCardMap);
					}
				}
				
				Collections.sort(cardsListCardList, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> card1, Map<String, Object> card2) {
						int cardOrder1 = (int) card1.get("card_order");
						int cardOrder2 = (int) card2.get("card_order");
						return Integer.compare(cardOrder1, cardOrder2);
					}
				});
				
				System.out.println("to server list::::" + cardsListCardList);
				
				cardsListMap.put("card", cardsListCardList);
				cardsList.add(cardsListMap);
			}
			
			
			resObj.put("cards_list", cardsList);
			
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping(path = "/delete/card")
	public @ResponseBody Object deleteCard (@RequestBody Map<String, Object> paramMap) {
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			System.out.println(paramMap);
			
			boardService.deleteCardPosition(paramMap);
			
			boardService.deleteCard(paramMap);
			
			// board_no, title
			resObj.put("board_no", paramMap.get("board_no"));
			resObj.put("title", paramMap.get("title"));
			
			// db에서 가져온 데이터 cards_list
			List<Map<String, Object>> cards_list = boardService.getCardList(paramMap);
			
			// db에서 가져온 데이터 card
			List<Map<String, Object>> card = boardService.getCard(paramMap);
			
			// resObj에 cards_list 로 가져올 리스트
			List<Map<String, Object>> cardsList = new ArrayList<Map<String, Object>>();
			
			// 가져온 cards_list 데이터를 반복시킴
			for(Map<String, Object> cardListMap: cards_list) {
				// map을 만들어서 하나씩 데이터를 새로운 key로 db에서 가져온 데이터를 삽입
				Map<String, Object> cardsListMap = new HashMap<String, Object>();
				cardsListMap.put("cards_list_no", cardListMap.get("cards_list_no"));
				cardsListMap.put("title", cardListMap.get("title"));
				cardsListMap.put("list_order", cardListMap.get("list_order"));
				// 새로운 card의 리스트 생성 
				List<Map<String, Object>> cardsListCardList = new ArrayList<Map<String, Object>>();
				// 가져온 card 데이터를 반복시킴
				for (Map<String, Object> cardMap: card) {
					// card와 card_list의 no이 같은것만 map에 삽입
					if(cardMap.get("card_list_no").equals(cardListMap.get("cards_list_no"))) {
						Map<String, Object> cardsListCardMap = new HashMap<String, Object>();
						cardsListCardMap.put("card_no", cardMap.get("card_no"));
						cardsListCardMap.put("contents", cardMap.get("contents"));
						cardsListCardMap.put("card_order", cardMap.get("card_order"));
						
						cardsListCardList.add(cardsListCardMap);
					}
				}
				
				Collections.sort(cardsListCardList, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> card1, Map<String, Object> card2) {
						int cardOrder1 = (int) card1.get("card_order");
						int cardOrder2 = (int) card2.get("card_order");
						return Integer.compare(cardOrder1, cardOrder2);
					}
				});
				
				System.out.println("to server list::::" + cardsListCardList);
				
				cardsListMap.put("card", cardsListCardList);
				cardsList.add(cardsListMap);
			}
			
			
			resObj.put("cards_list", cardsList);
			return resObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping(path = "/edit/title")
	public @ResponseBody Object editBoardTitle(@RequestBody Map<String, Object> paramMap) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			boardService.editBoardTitle(paramMap);
			
			// board_no, title
			resObj.put("board_no", paramMap.get("board_no"));
			resObj.put("title", paramMap.get("title"));
			
			// db에서 가져온 데이터 cards_list
			List<Map<String, Object>> cards_list = boardService.getCardList(paramMap);
			
			// db에서 가져온 데이터 card
			List<Map<String, Object>> card = boardService.getCard(paramMap);
			
			// resObj에 cards_list 로 가져올 리스트
			List<Map<String, Object>> cardsList = new ArrayList<Map<String, Object>>();
			
			// 가져온 cards_list 데이터를 반복시킴
			for(Map<String, Object> cardListMap: cards_list) {
				// map을 만들어서 하나씩 데이터를 새로운 key로 db에서 가져온 데이터를 삽입
				Map<String, Object> cardsListMap = new HashMap<String, Object>();
				cardsListMap.put("cards_list_no", cardListMap.get("cards_list_no"));
				cardsListMap.put("title", cardListMap.get("title"));
				cardsListMap.put("list_order", cardListMap.get("list_order"));
				// 새로운 card의 리스트 생성 
				List<Map<String, Object>> cardsListCardList = new ArrayList<Map<String, Object>>();
				// 가져온 card 데이터를 반복시킴
				for (Map<String, Object> cardMap: card) {
					// card와 card_list의 no이 같은것만 map에 삽입
					if(cardMap.get("card_list_no").equals(cardListMap.get("cards_list_no"))) {
						Map<String, Object> cardsListCardMap = new HashMap<String, Object>();
						cardsListCardMap.put("card_no", cardMap.get("card_no"));
						cardsListCardMap.put("contents", cardMap.get("contents"));
						cardsListCardMap.put("card_order", cardMap.get("card_order"));
						
						cardsListCardList.add(cardsListCardMap);
					}
				}
				
				Collections.sort(cardsListCardList, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> card1, Map<String, Object> card2) {
						int cardOrder1 = (int) card1.get("card_order");
						int cardOrder2 = (int) card2.get("card_order");
						return Integer.compare(cardOrder1, cardOrder2);
					}
				});
				
				System.out.println("to server list::::" + cardsListCardList);
				
				cardsListMap.put("card", cardsListCardList);
				cardsList.add(cardsListMap);
			}
			
			
			resObj.put("cards_list", cardsList);
			
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
