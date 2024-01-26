import Card from "./Card";


function MyPage ({userPosts ,onDeleteCard}){
   
    return (
        <>
        <h1 className="my_page_title">My Page</h1>
        { userPosts.length===0? <p className="my_card_message">Add Post to MyCards!</p> : <Card className={"my_page_cards"} data={userPosts} onDeleteCard={onDeleteCard} inMyPage={true}/>}
        </>
    )
}
export default MyPage;