/*
사용할 더미 API: https://dummyjson.com
요구사항:
1. 페이지에 진입하여 '포스트 로드' 버튼을 누르면 dummyjson에서 포스트 10개를 가져와 렌더링 해준다.
2. 이미 포스트가 렌더링 된 상태에서 한번 더 '포스트 로드' 버튼을 누르면 다음 포스트 10개를 가져와 렌더링한다.
3. 포스트는 제목과 요약된 본문, 태그, '코멘트 로드' 버튼으로 구성된다.
4. '코멘트 로드' 버튼을 누르면 최대 3개까지 해당 포스트의 코멘트를 가져와 렌더링한다.
5. 코멘트는 페이징을 하지 않는다.
 */

const postBoard = document.getElementById('post-board')
const loadPostButton = document.getElementById('load-post-more')

let currentPage = 1

// 포스트를 10개 가져와서 console.log에 찍는 함수 만들기
async function getPosts(page) {
  // 1번 페이지 = 1번쨰 ~ 10번째
  // 2번 페이지 = 11번째 ~ 20번째
  if (!page) page = 1
  const skip = (page - 1) * 10
  const res = await fetch(`https://dummyjson.com/posts?limit=10&skip=${skip}`)
  const data = await res.json()
  console.log(data)
  return data.posts
}

function buildPostElement(post) {
  const ellipsisCount = 150
  const summary = (post.body.length > ellipsisCount)
    ? post.body.slice(0, ellipsisCount) + '...'
    : post.body

  const tagsArray = post.tags.map((item) => `#${item}`)

  return `<div class="post-item">
<div class="post-title">${post.title}</div>
<div class="post-summary">${summary}</div>
<div class="post-tags">${tagsArray.join(', ')}</div> 
<div class="comment-button" onclick="loadComments(${post.id})">comment</div>
<div class="comment-box" id="post-${post.id}-comment"></div>
</div>`
}

async function loadPosts() {
  try {
    const posts = await getPosts(currentPage)
    posts.forEach((post) => {
      const element = buildPostElement(post)
      postBoard.innerHTML += element
    })
    currentPage += 1
  } catch (e) {
    console.log(e)
  }
}

async function getComments(postId) {
  const res = await fetch(`https://dummyjson.com/comments/post/${postId}?limit=3`)
  const data = await res.json()
  return data.comments
}

function buildCommentElement(comment) {
  return `
<div class="comment-item">
<div class="comment-user">${comment.user.username}</div>
<div class="comment-body">${comment.body}</div>
</div>  
`
}

async function loadComments(postId) {
  const comments = await getComments(postId)
  const commentBox = document.getElementById(`post-${postId}-comment`)
  const commentElements = comments.map((comment) => {
    return buildCommentElement(comment)
  })
  commentBox.innerHTML = commentElements.join('')
}

loadPostButton.addEventListener('click', () => {
  loadPosts()
})