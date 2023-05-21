// modal.js

const createModal = (title, content) => {
    const modalEl = document.createElement('div');
    modalEl.classList.add('modal', 'fade');
    modalEl.setAttribute('id', 'errorModal');
    modalEl.setAttribute('tabindex', '-1');
    modalEl.setAttribute('role', 'dialog');
    modalEl.setAttribute('aria-labelledby', 'errorModalLabel');
    modalEl.setAttribute('aria-hidden', 'true');

    modalEl.innerHTML = `
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="errorModalLabel">${title}</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          ${content}
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  `;

    return modalEl;
};
export default createModal;