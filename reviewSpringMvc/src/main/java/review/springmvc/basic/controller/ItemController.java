//package review.springmvc.basic.controller;

//@Controller
//@RequiredArgsConstructor
//@Slf4j
//public class ItemController {
//    private final ItemRepository repository;
//
//    @GetMapping("/basic/items")
//    public String items(Model model) {
//        List<Item> items = repository.getAll();
//        model.addAttribute("items",items);
//        return "basic/items"; //Controller 컴포넌트에서 String을 리턴하면 resource 폴더의 해당 경로의 뷰에다가 모델 반환
//    }
//
//    @GetMapping("/basic/items/{itemId}")
//    public String getItem(@PathVariable int itemId, Model model){
//        Item item = repository.findById(itemId);
//        model.addAttribute("item", item);
//        return "basic/item";
//    }
//
//    @GetMapping("/basic/items/{itemId}/remove")
//    public String remove(Model model, @PathVariable Integer itemId){
//        repository.delete(itemId);
//        model.addAttribute("items",repository.getAll());
//        return "redirect:/basic/items";
//    }
//
//    @GetMapping("/basic/items/add")
//    public String add(){
//        return "basic/addForm";
//    }
//
//    @PostMapping("/basic/items/add")
//    public String addItem(@ModelAttribute Item item, Model model){
//        repository.save(item);
//        model.addAttribute("items", repository.getAll());
//        return "redirect:/basic/items"; //해당 주소로 보내줌
//        //모델도 같이 넘겨주는듯?
//    }
//
//    @GetMapping("/basic/items/{itemId}/edit")
//    public String edit(Model model, @PathVariable Integer itemId){//바로 위 코드의 {itemId}를 사용하기 위해 PathVariable 사용
//        model.addAttribute("item", repository.findById(itemId));
//        return "basic/editForm";
//    }
//
//    @PostMapping("/basic/items/{itemId}/edit")
//    public String editFinish(@ModelAttribute Item item, @PathVariable Integer itemId){
//        repository.update(item);
//        return "redirect:/basic/items"; //리턴할 때 맨 앞에 / 빼먹는거 주의
//    }
//
//    @GetMapping("/basic/items/{itemId}/buy")
//    public String buyItem(@PathVariable Integer itemId, Model model) {
//        repository.buy(itemId);
//        model.addAttribute(itemId);
//        return "redirect:/basic/items/{itemId}";
//    }
//}
